package com.home.netty.im.codec;

import com.home.netty.im.protocol.Packet;
import com.home.netty.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 9:11 2019/9/24
 * @modified by:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodec.INSTANCE.encode(byteBuf, packet);
    }
}
