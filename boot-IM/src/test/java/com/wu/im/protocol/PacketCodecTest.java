package com.wu.im.protocol;

import com.wu.im.codec.JSONSerializer;
import com.wu.im.codec.PacketCodec;
import com.wu.im.codec.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Assert;
import org.junit.Test;

public class PacketCodecTest {

    @Test
    public void encoder(){

        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("password");

        ByteBuf byteBuf = PacketCodec.INSTANCE.encode(ByteBufAllocator.DEFAULT.ioBuffer(),loginRequestPacket);
        Packet decodedPacket = PacketCodec.INSTANCE.decode(byteBuf);
        System.out.println(decodedPacket.getCommand());
        System.out.println("------------");
        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }
}
