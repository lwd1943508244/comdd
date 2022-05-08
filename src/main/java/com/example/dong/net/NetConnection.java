package com.example.dong.net;

import com.example.dong.message.Message;
import com.example.dong.pojo.User;
import io.netty.channel.ChannelHandlerContext;

/*
* 用户连接类
* */
public class NetConnection {
    public User user;
    public ChannelHandlerContext ctx;
    public NetConnection(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }
    public void sendMsg(Message msg){
        if (ctx != null){
            ctx.writeAndFlush(msg);
        }
    }
}
