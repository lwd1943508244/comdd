package com.example.dong.message;

public class RegisterRespondMessage extends Message{
    public Boolean flag = false; //默认值为false
    @Override
    public int getCode() {
        return 1010;
    }
}
