package com.example.telegramnotificationservice.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private long idChat;
    private String text;
}