package com.wu.im.codec;

import com.wu.im.protocol.*;
import com.wu.im.protocol.request.*;
import com.wu.im.protocol.response.*;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.wu.im.protocol.Command.*;

public class PacketCodec {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodec INSTANCE = new PacketCodec();

    private PacketCodec(){

    }

    public static final Map<Byte,Class<? extends Packet>> requestTypeMap;
    public static final Map<Byte,Serializer> serializerMap;

    static {
        requestTypeMap = new HashMap<>();
        requestTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        requestTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        requestTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        requestTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        requestTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        requestTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        requestTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        requestTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        requestTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        requestTypeMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        requestTypeMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        requestTypeMap.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
//        requestTypeMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
//        requestTypeMap.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        requestTypeMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        requestTypeMap.put(GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);
//        requestTypeMap.put(HEARTBEAT_REQUEST, HeartBeatRequestPacket.class);
//        requestTypeMap.put(HEARTBEAT_RESPONSE, HeartBeatResponsePacket.class);


        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(),serializer);
    }
    /**
     * 编码
     * @param packet
     * @return
     */
    public ByteBuf encode(ByteBuf byteBuf ,Packet packet){


        //序列化对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;

    }


    /**
     * 解码
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf){
        //跳过magic number
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);
        //序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        //指令
        byte command = byteBuf.readByte();
        //数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];

        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);

        Serializer serializer = getSerializer( serializeAlgorithm);

        return  serializer.deserialize(requestType ,bytes);

    }


    private Serializer getSerializer(byte serializeAlgorithm){
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command){
        return requestTypeMap.get(command);
    }


}
