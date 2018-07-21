package com.wu.netty.cpt8.codec;


import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

/**
 * Created by 天&赐&清 on 2018/7/17.
 * 消息解码工具类
 */
public class MarshallingDecoder {
    private final Unmarshaller unmarshaller ;

    public MarshallingDecoder() throws IOException {
        this.unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
    }

    protected Object decode(ByteBuf in) throws Exception {
        try {
            int objectSize = in.readInt();
            ByteBuf buf = in.slice(in.readerIndex(), objectSize);
            ByteInput input = new ChannelBufferByteInput(buf);
            unmarshaller.start(input);
            Object object = unmarshaller.readObject();
            unmarshaller.finish();
            in.readerIndex(in.readerIndex()+objectSize);
            return object;
        } finally {
            unmarshaller.close();
        }
    }
}
