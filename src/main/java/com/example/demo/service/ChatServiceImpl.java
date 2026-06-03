package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.ChatMessageDTO;
import com.example.demo.dtos.ChatRoomDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Chat;
import com.example.demo.entities.ChatParticipant;
import com.example.demo.entities.Message;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ChatParticipantRepository;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.MessageRepository;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatParticipantRepository chatParticipantRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ChatMessageDTO sendMessage(ChatMessageDTO chatMessageDTO) {
        Chat chat = chatRepository.findById(chatMessageDTO.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        Account sender = accountRepository.findById(chatMessageDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Message message = new Message();
        message.setChat(chat);
        message.setAccount(sender);
        message.setContent(chatMessageDTO.getContent());
        message.setType(chatMessageDTO.getType());
        message.setCreatedAt(new Date());
        message.setAttachmentPhoto(chatMessageDTO.getAttachmentUrl());
        message.setIsDeleted(false);

        Message savedMessage = messageRepository.save(message);

        // Update chat timestamp or status if needed
        chat.setStatusChat("ACTIVE");
        chatRepository.save(chat);

        ChatMessageDTO responseDTO = mapToDTO(savedMessage);

        // Broadcast to WebSocket topic (for active chat room)
        messagingTemplate.convertAndSend("/topic/chat/" + chat.getId(), responseDTO);

        // Broadcast refresh signal to all participants (for sidebar sync)
        broadcastChatListUpdate(chat);

        return responseDTO;
    }

    @Override
    public List<ChatRoomDTO> getChatsForUser(Integer userId) {
        List<ChatParticipant> participants = chatParticipantRepository.findByAccountId(userId);
        List<ChatRoomDTO> chatRoomDTOs = new ArrayList<>();

        for (ChatParticipant participant : participants) {
            Chat chat = participant.getChat();

            // Find the other participant to get their name/avatar
            Optional<ChatParticipant> otherParticipantOpt = chat.getChatParticipants().stream()
                    .filter(p -> !p.getAccount().getId().equals(userId))
                    .findFirst();

            ChatRoomDTO dto = new ChatRoomDTO();
            dto.setChatId(chat.getId());
            dto.setStatus(chat.getStatusChat());

            if (otherParticipantOpt.isPresent()) {
                Account otherAccount = otherParticipantOpt.get().getAccount();
                dto.setChatName(otherAccount.getFullName());
                dto.setChatAvatar(otherAccount.getAvatar());
            } else {
                dto.setChatName("Unknown User");
            }

            // Get last message efficiently
            messageRepository.findFirstByChatIdOrderByCreatedAtDesc(chat.getId())
                    .ifPresent(lastMsg -> {
                        dto.setLastMessage(lastMsg.getContent());
                        dto.setLastMessageTime(lastMsg.getCreatedAt());
                    });

            chatRoomDTOs.add(dto);
        }

        // Sort by last message time desc (newest first), nulls at the end
        chatRoomDTOs.sort(java.util.Comparator.comparing(ChatRoomDTO::getLastMessageTime,
                java.util.Comparator.nullsLast(java.util.Comparator.reverseOrder())));

        return chatRoomDTOs;
    }

    @Override
    public Page<ChatMessageDTO> getMessagesForChat(Integer chatId, Pageable pageable) {
        Page<Message> messages = messageRepository.findByChatIdOrderByCreatedAtDesc(chatId, pageable);
        return messages.map(this::mapToDTO);
    }

    @Override
    public Integer createOrGetChat(Integer senderId, Integer recipientId) {
        try {
            List<ChatParticipant> userChats = chatParticipantRepository.findByAccountId(senderId);

            for (ChatParticipant p : userChats) {
                Chat chat = p.getChat();
                boolean recipientInChat = chat.getChatParticipants().stream()
                        .anyMatch(cp -> cp.getAccount().getId().equals(recipientId));
                if (recipientInChat) {
                    return chat.getId();
                }
            }

            // Create new chat
            Chat newChat = new Chat();
            newChat.setCreatedAt(new Date());
            newChat.setStatusChat("ACTIVE");
            newChat = chatRepository.save(newChat);

            Account sender = accountRepository.findById(senderId)
                    .orElseThrow(() -> new RuntimeException("Sender not found: " + senderId));
            Account recipient = accountRepository.findById(recipientId)
                    .orElseThrow(() -> new RuntimeException("Recipient not found: " + recipientId));

            Message welcomeMsg = new Message();
            welcomeMsg.setAccount(sender);
            welcomeMsg.setChat(newChat);
            welcomeMsg.setContent("Chat started");
            welcomeMsg.setType("SYSTEM");
            welcomeMsg.setCreatedAt(new Date());
            welcomeMsg.setIsDeleted(false);
            welcomeMsg = messageRepository.save(welcomeMsg);

            ChatParticipant senderParticipant = new ChatParticipant();
            senderParticipant.setAccount(sender);
            senderParticipant.setChat(newChat);
            senderParticipant.setRoleInParticipant(getAccountRole(sender)); // FIXED: Dynamic role
            senderParticipant.setMessage(welcomeMsg);
            chatParticipantRepository.save(senderParticipant);

            ChatParticipant recipientParticipant = new ChatParticipant();
            recipientParticipant.setAccount(recipient);
            recipientParticipant.setChat(newChat);
            recipientParticipant.setRoleInParticipant(getAccountRole(recipient)); // FIXED: Dynamic role
            recipientParticipant.setMessage(welcomeMsg);
            chatParticipantRepository.save(recipientParticipant);

            // Notify both participants to refresh their lists
            broadcastChatListUpdate(newChat);

            return newChat.getId();
        } catch (Exception e) {
            throw e; // Rethrow to trigger rollback
        }
    }

    private String getAccountRole(Account account) {
        if (account.getRoleAccounts() == null || account.getRoleAccounts().isEmpty()) {
            return "USER";
        }

        return account.getRoleAccounts().stream()
                .map(ra -> ra.getRole().getNameRole())
                .map(role -> role.startsWith("ROLE_") ? role.substring(5) : role)
                .filter(role -> role.equals("ADMIN") || role.equals("SELLER") || role.equals("USER"))
                .findFirst()
                .orElse("USER");
    }

    private void broadcastChatListUpdate(Chat chat) {
        if (chat.getChatParticipants() != null) {
            chat.getChatParticipants().forEach(p -> {
                messagingTemplate.convertAndSend("/topic/user-chats/" + p.getAccount().getId(), "REFRESH");
            });
        }
    }

    private ChatMessageDTO mapToDTO(Message message) {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setId(message.getId());
        dto.setChatId(message.getChat().getId());
        dto.setSenderId(message.getAccount().getId());
        dto.setSenderName(message.getAccount().getFullName());
        dto.setSenderAvatar(message.getAccount().getAvatar());
        dto.setContent(message.getContent());
        dto.setType(message.getType());
        dto.setCreatedAt(message.getCreatedAt());
        dto.setAttachmentUrl(message.getAttachmentPhoto());
        return dto;
    }
}
