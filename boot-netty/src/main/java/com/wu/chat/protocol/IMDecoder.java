package com.wu.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.msgpack.MessagePack;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解码器
 */
public class IMDecoder extends ByteToMessageDecoder {

    //解析IM写一下请求内容的正则
    private Pattern pattern = Pattern.compile("^\\[(.*)\\](\\s\\-\\s(.*))?");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final int length = in.readableBytes();
        final byte[] arr = new byte[length];

        String content = new String(arr,in.readerIndex(),length);

        if(!StringUtils.isEmpty(content)){
            if(!IMP.isIMP(content)){
                ctx.pipeline().remove(this);
                return;
            }
        }

        in.getBytes(in.readerIndex(),arr ,0, length);
        out.add(new MessagePack().read(arr,IMMessage.class));
        out.clear();
    }


    public IMMessage decoder(String msg){
        if(null == msg || "".equals(msg.trim())){ return null; }
        try{
            Matcher m = pattern.matcher(msg);
            String header = "";
            String content = "";
            if(m.matches()){
                header = m.group(1);
                content = m.group(3);
            }

            String [] heards = header.split("\\]\\[");
            long time = 0;
            try{ time = Long.parseLong(heards[1]); } catch(Exception e){}
            String nickName = heards[2];
            //昵称最多十个字
            nickName = nickName.length() < 10 ? nickName : nickName.substring(0, 9);

            if(msg.startsWith("[" + IMP.LOGIN.getName() + "]")){
                return new IMMessage(heards[0],time,nickName);
            }else if(msg.startsWith("[" + IMP.CHAT.getName() + "]")){
                return new IMMessage(heards[0],time,nickName,content);
            }else if(msg.startsWith("[" + IMP.FLOWER.getName() + "]")){
                return new IMMessage(heards[0],time,nickName);
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
