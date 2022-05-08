package com.example.dong.test;

import com.example.dong.message.LoginRequestMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建启动器类
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        new Bootstrap()
                // 2. 添加EventLoop
                .group(new NioEventLoopGroup())
                // 3. 选择客户端的Channel事件
                .channel(NioSocketChannel.class)
                // 4. 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override  //在连接建立后被调用
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(LOGGING_HANDLER);
                        ch.pipeline().addLast(new StringEncoder());  //把字符串编码成ByteBuf
                    }
                })
                // 5. 连接到服务器
                .connect(new InetSocketAddress("127.0.0.1",5000))
                .sync()   //阻塞方法直到连接建立  channel代表连接对象
                .channel()
                // 6. 向服务器发送数据
                .writeAndFlush("hello netty");
    }
}
