package com.wu.im.session;

import com.wu.im.util.Attributes;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    //userId---channel的映射
    private static final Map<String,Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static void bingSession(Session session , Channel channel){
        userIdChannelMap.put(session.getUserId(),channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBingSession(Channel channel){
        if(hasLogin(channel)){
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }


    public static boolean hasLogin(Channel channel){
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }


    public static Channel getChannel(String userId){
        return userIdChannelMap.get(userId);
    }
}
