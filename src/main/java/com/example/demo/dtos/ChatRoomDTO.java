package com.example.demo.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDTO {
    private Integer chatId;
    private String chatName; // Often the other participant's name
    private String chatAvatar; // Often the other participant's avatar
    private String lastMessage;
    private Date lastMessageTime;
    private Integer unreadCount;
    private String status; // active, archived, etc.
}
