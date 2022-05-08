package com.example.dong.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message  implements Serializable {

    public String username;
    public String password;
    public int code;
    public abstract int getCode();

    public static final int RegisterRequestMessageMessage = 1000; //注册消息
    public static final int LoginRequestMessage = 1001;   //登录请求消息
    public static final int ChatMessage = 1002;  //登录响应消息

    public static final int RegisterRespondMessage = 1010; //注册返回消息
    public static final int LoginRespondMessage = 1011; //登录返回消息

    public String toJson() {
        return JSON.toJSONString(this, SerializerFeature.IgnoreErrorGetter);
    }
}