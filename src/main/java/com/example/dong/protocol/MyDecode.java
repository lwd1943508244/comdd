package com.example.dong.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

public class MyDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        in.markReaderIndex();
        int preIndex = in.readerIndex();
        //返回内容的长度
        int length = readRawInt32(in);
        if (preIndex == in.readerIndex()) {
            return;
        }
        if (length < 0) {
            throw new CorruptedFrameException("negative length: " + length);
        }
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
        } else {
            //前面都是异常情况
            //解码
            byte[] body = new byte[length];
            in.readBytes(body);
            String str = new String(body);
            out.add(str);
        }

    }
    /**
     * 自定义实现,没有采用 varint32
     *
     * @param buffer
     * @return
     */
    private static int readRawInt32(ByteBuf buffer) {
        if (!buffer.isReadable()) {
            return 0;
        }

        // 如果可读 字节 不足 int,返回0
        if (buffer.readableBytes() < 4) {
            return 0;
        }

        buffer.markReaderIndex();

        int readInt = buffer.readInt();
        if (readInt <= 0) {
            buffer.resetReaderIndex();
            return 0;
        } else {
            return readInt;
        }
    }
}
