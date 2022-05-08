package com.example.dong.message;

public class LoginRespondMessage extends Message{
    public Boolean flag = false; //默认值为false
    @Override
    public int getCode() {
        return LoginRespondMessage;
    }
}
