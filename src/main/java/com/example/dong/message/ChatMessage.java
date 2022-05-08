package com.example.dong.message;

public class ChatMessage extends Message{
    private String chat;
    @Override
    public int getCode() {
        return 2;
    }
}
