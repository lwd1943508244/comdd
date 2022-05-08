package com.example.dong.test;

import com.example.dong.message.LoginRequestMessage;
import com.example.dong.protocol.MessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

public class TestCode {
    public static void main(String[] args) throws Exception {
        EmbeddedChannel channel = new EmbeddedChannel(
                //解决粘包,半包,属于线程不安全
                new LengthFieldBasedFrameDecoder(
                        1024,12,4,0,0),
                //打印日志类信息
                new LoggingHandler(),
                new MessageCodec());

        //encode
        LoginRequestMessage message = new LoginRequestMessage("zhangsan","123");
        channel.writeOutbound(message);

        //decode
       /* ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null,message,buf);
        channel.writeInbound(buf);*/
    }
}
