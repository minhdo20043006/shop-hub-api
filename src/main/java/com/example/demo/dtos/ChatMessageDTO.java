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
public class ChatMessageDTO {
    private Integer id;
    private Integer chatId;
    private Integer senderId;
    private String senderName;
    private String senderAvatar;
    private String content;
    private String type; // TEXT, IMAGE, VIDEO
    private Date createdAt;
    private String attachmentUrl;
}
