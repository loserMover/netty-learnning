package com.home.netty.im.protocol;

import com.home.netty.im.protocol.command.Command;
import com.home.netty.im.protocol.request.LoginRequestPacket;
import com.home.netty.im.protocol.response.LoginResponsePacket;
import com.home.netty.im.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wulj13232
 * @description:
 * @date: Created in 17:49 2019/9/20
 * @modified by:
 */
public class PacketCodec {

    private static final int MAGIC_NUMBER = 0X12345678;

    public static final PacketCodec INSTANCE = new PacketCodec();

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);

        serializerMap = new HashMap<>();
        serializerMap.put(Serializer.DEFAULT.getSerializerAlgorithm(), Serializer.DEFAULT);
    }

    /**
     * 通信协议编码
     * @param packet
     * @return
     */
    public ByteBuf encode(Packet packet){
        //1.创建ByteBuf 对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        //2.序列化传送对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //3.通信协议编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 通信协议解码
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf){
        //跳过 magic number
        byteBuf.skipBytes(4);

        //跳过协议版本号
        byteBuf.skipBytes(1);

        //获取序列化算法
        byte serializerAlgorithm = byteBuf.readByte();

        //获取指令类型
        byte command = byteBuf.readByte();

        //获取数据长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if(null != requestType && null != serializer) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    /**
     * 获取请求类型
     * @param command
     * @return
     */
    private Class<? extends Packet> getRequestType(byte command){
        return packetTypeMap.get(command);
    }

    /**
     * 获取序列化对象
     * @param serializerAlgorithm
     * @return
     */
    private Serializer getSerializer(byte serializerAlgorithm){
        return serializerMap.get(serializerAlgorithm);
    }
}
