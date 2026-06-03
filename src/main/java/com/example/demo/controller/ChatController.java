package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ChatMessageDTO;
import com.example.demo.dtos.ChatRoomDTO;
import com.example.demo.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // WebSocket Endpoint: /app/chat.sendMessage
    @MessageMapping("/chat.sendMessage")
    public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessageDTO) {
        return chatService.sendMessage(chatMessageDTO);
    }

    // REST Endpoint: Send message via POST
    @PostMapping("/send")
    public ResponseEntity<ChatMessageDTO> sendMessageRest(
            @org.springframework.web.bind.annotation.RequestBody ChatMessageDTO chatMessageDTO) {
        return ResponseEntity.ok(chatService.sendMessage(chatMessageDTO));
    }

    // REST Endpoint: Get user's chats
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatRoomDTO>> getUserChats(@PathVariable Integer userId) {
        return ResponseEntity.ok(chatService.getChatsForUser(userId));
    }

    // REST Endpoint: Get messages for a chat
    @GetMapping("/{chatId}/messages")
    public ResponseEntity<Page<ChatMessageDTO>> getChatMessages(
            @PathVariable Integer chatId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(chatService.getMessagesForChat(chatId, pageable));
    }

    // REST Endpoint: Create or Get Chat
    @PostMapping("/create")
    public ResponseEntity<Integer> createOrGetChat(@RequestParam Integer senderId, @RequestParam Integer recipientId) {
        return ResponseEntity.ok(chatService.createOrGetChat(senderId, recipientId));
    }
}
