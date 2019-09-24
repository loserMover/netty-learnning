package com.home.netty.im.codec;

import com.home.netty.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 13:50 2019/9/24
 * @modified by:
 */
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if(in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUMBER){
            System.out.println("当协议不符合规则，则关闭对应的连接");
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
