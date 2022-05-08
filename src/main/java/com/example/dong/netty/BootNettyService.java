package com.example.dong.netty;

import com.example.dong.protocol.MyDecode;
import com.example.dong.protocol.MyEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BootNettyService {
    public void bind(int port) {
        System.out.println("lianjie"+port);
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        BootNettyChannelInboundHandlerAdapter handlerAdapter = new BootNettyChannelInboundHandlerAdapter();
        MyDecode MYDECODE = new MyDecode();
        MyEncoder MYENCODER = new MyEncoder();

        try {
            bootstrap.group(boss,worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                 //   ch.pipeline().addLast(new LengthFieldBasedFrameDecoder
                    //            (1024,12,4,0,0));
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MYDECODE); //解码器
                    ch.pipeline().addLast(MYENCODER); //编码器
                    ch.pipeline().addLast(handlerAdapter); //入栈消息处理器
                }
            });

            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }


}
