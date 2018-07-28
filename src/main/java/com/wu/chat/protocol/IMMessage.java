package com.wu.chat.protocol;

public class IMMessage {

    /**
     * ip地址及端口
     */
    private String addr ;
    /**
     * 命令 login /logout / system
     */
    private String cmd ;

    /**
     * 时间
     */
    private Long time ;

    /**
     * 在线人数
     */
    private Integer online ;

    /**
     * 发送人
     */
    private String sender ;
    /**
     * 接收人
     */
    private String receiver ;

    /**
     * 发送内容
     */
    private String content ;

    public IMMessage(){

    }

    public IMMessage(String cmd, Long time, Integer online, String content) {
        this.cmd = cmd;
        this.time = time;
        this.online = online;
        this.content = content;
    }

    public IMMessage(String cmd, Long time, String sender) {
        this.cmd = cmd;
        this.time = time;
        this.sender = sender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
