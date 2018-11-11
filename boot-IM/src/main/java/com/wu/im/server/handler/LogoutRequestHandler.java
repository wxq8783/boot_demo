package com.wu.im.server.handler;

import com.wu.im.protocol.request.LogoutRequestPacket;
import com.wu.im.protocol.response.LogoutResponsePacket;
import com.wu.im.session.Session;
import com.wu.im.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        LogoutResponsePacket packet = new LogoutResponsePacket();
        Session session = null;
        String userId = msg.getUserId();
        if(StringUtils.isEmpty(userId)){
            session = SessionUtil.getSession(ctx.channel());
            if(session == null){
                packet.setSuccess(false);
                packet.setReason("获取不到session");
                ctx.channel().writeAndFlush(packet);
                return;
            }
        }
        userId = session.getUserId();
        SessionUtil.unBingSession(ctx.channel());
        packet.setSuccess(true);
        packet.setUserId(userId);
        packet.setUserName(session.getUserName());
        ctx.channel().writeAndFlush(packet);
    }

}
