package com.example.dong.protocol;

import com.example.dong.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class MyEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        if(msg == null){
            return;
        }
        byte[] bytes = msg.toJson().getBytes();
        byte[] bytes1 = add4Bytes(bytes);
        out.writeBytes(bytes1);
    }

    // 俩数组合并,这里  因为客户端使用的是C#,需要采用小端方式编入int
    public static byte[] add4Bytes(byte[] data2) {
        //数组结束位,存放内存起始位, 即:高位在后
        int num = data2.length;
        byte[] data1 = new byte[4];
        data1[0] = (byte) (num & 0xff);
        data1[1] = (byte) (num >> 8 & 0xff);
        data1[2] = (byte) (num >> 16 & 0xff);
        data1[3] = (byte) (num >> 24 & 0xff);


        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }

}
