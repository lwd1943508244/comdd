package com.example.dong.net;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dong.config.SpringBeanUtil;
import com.example.dong.message.LoginRequestMessage;
import com.example.dong.message.RegisterRequestMessage;
import com.example.dong.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/*
* 消息分发处理
* 负责把消息分类
* */

@Component
public class MessageDispatch {
    static UserService userService;
    public MessageDispatch() {
        userService = SpringBeanUtil.getBean(UserService.class);
    }

    // 单例
    public final static MessageDispatch Instance = new MessageDispatch();

    public void receiveData(ChannelHandlerContext ctx, String message) {
        JSONObject msg = JSON.parseObject(message);
        int code = (int)msg.get("code");
        NetConnection netCon = new NetConnection(ctx);
        switch (code) {
            case 1000 -> {
                RegisterRequestMessage rMsg = JSON.parseObject(message, RegisterRequestMessage.class);
                userService.register(netCon, rMsg.getUsername(), rMsg.getPassword());
            }
            case 1001 -> {
                LoginRequestMessage lMsg = JSON.parseObject(message, LoginRequestMessage.class);
                userService.login(netCon, lMsg.getUsername(), lMsg.getPassword());
            }
        }

    }
}
