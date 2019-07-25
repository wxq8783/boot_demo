package com.wu.chat.process;

import com.alibaba.fastjson.JSONObject;
import com.wu.chat.protocol.IMDecoder;
import com.wu.chat.protocol.IMEncoder;
import com.wu.chat.protocol.IMMessage;
import com.wu.chat.protocol.IMP;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

public class IMProcessor {

    private final static ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private IMDecoder decoder = new IMDecoder();

    private IMEncoder encoder = new IMEncoder();

    private AttributeKey<String> NIKE_NAME =  AttributeKey.valueOf("nikeName");
    private AttributeKey<JSONObject> ATTRS = AttributeKey.valueOf("attrs");

    public void process(Channel client , String msg){
        IMMessage request = decoder.decoder(msg);

        if(request == null){
            return;
        }
        String nikeName = request.getSender();
        //登录
        if(IMP.LOGIN.getName().equals(request.getCmd())){
            onlineUsers.add(client);

            client.attr(NIKE_NAME).getAndSet(request.getSender());

            for(Channel channel : onlineUsers){
                //不是自己
                if(channel != client){
                    request = new IMMessage(IMP.SYSTEM.getName(),System.currentTimeMillis(),onlineUsers.size(),nikeName);
                }else{
                    request = new IMMessage(IMP.SYSTEM.getName(),System.currentTimeMillis(),onlineUsers.size(),"欢迎");
                }
                String text = encoder.encode(request);
                channel.writeAndFlush(new TextWebSocketFrame(text));
            }
        }
        //退出
        else if(IMP.LOGOUT.getName().equals(request.getCmd())){
            onlineUsers.remove(client);
        }else if(IMP.CHAT.getName().equals(request.getCmd())){
            for(Channel channel : onlineUsers){
                //不是自己
                if(channel != client){
                    request.setSender(client.attr(NIKE_NAME).get());
                }else{
                    request.setSender("you");
                }
                String text = encoder.encode(request);
                channel.writeAndFlush(new TextWebSocketFrame(text));
            }

        }else if(IMP.FLOWER.getName().equals(request.getCmd())){
            JSONObject jsonObject = client.attr(ATTRS).get();
            Long currentTime = System.currentTimeMillis();
            if(null != jsonObject){
                Long lastTime = jsonObject.getLong("lastFlowerTime");
                Long sub = currentTime - lastTime;
                if(sub < 10*1000){
                    request.setSender("you");
                    request.setCmd(IMP.SYSTEM.getName());
                    request.setContent("你送的鲜花太频繁，请稍后在试试");
                    String content = encoder.encode(request);
                    client.writeAndFlush(new TextWebSocketFrame(content));
                    return;
                }
            }
            for(Channel channel : onlineUsers){
                //不是自己
                if(channel != client){
                    request.setSender(client.attr(NIKE_NAME).get());
                    request.setContent(client.attr(NIKE_NAME).get()+" 送来一波鲜花雨");
                }else{
                    request.setSender("you");
                    request.setContent("你给大家送了一波鲜花雨");
                    setAttrs(channel,"lastFlowerTime",String.valueOf(System.currentTimeMillis()));
                }
                request.setTime(System.currentTimeMillis());
                String text = encoder.encode(request);
                channel.writeAndFlush(new TextWebSocketFrame(text));
            }

        }

    }

    public void  logout(Channel client){
        onlineUsers.remove(client);
    }

    private JSONObject getAttre(Channel client){
        return client.attr(ATTRS).get();
    }

    private void setAttrs(Channel client , String key , String value){
        try {
            JSONObject object = client.attr(ATTRS).get();
            object.put(key,value);
            client.attr(ATTRS).set(object);
        } catch (Exception e) {
            JSONObject object = new JSONObject();
            object.put(key,value);
            client.attr(ATTRS).set(object);
        }
    }
}
