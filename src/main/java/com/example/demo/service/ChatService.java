package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dtos.ChatMessageDTO;
import com.example.demo.dtos.ChatRoomDTO;

public interface ChatService {
    ChatMessageDTO sendMessage(ChatMessageDTO chatMessageDTO);

    List<ChatRoomDTO> getChatsForUser(Integer userId);

    Page<ChatMessageDTO> getMessagesForChat(Integer chatId, Pageable pageable);

    Integer createOrGetChat(Integer senderId, Integer recipientId);
}
