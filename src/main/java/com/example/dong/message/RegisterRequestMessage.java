package com.example.dong.message;

import lombok.Data;

@Data
public class RegisterRequestMessage extends Message{
    private Integer uid;
    private String username;
    private String password;
    @Override
    public int getCode() {
        return RegisterRequestMessageMessage;
    }
}
