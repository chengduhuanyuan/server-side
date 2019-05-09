package com.hy.serverside.entity;

/**
 * @author 杨席杰
 * @date 2019/5/9 17:16
 */

public class TextMessage {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;

    public String getToUserName() {
        return ToUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public String getContent() {
        return Content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    @Override
    public String toString() {
        return "TextMessage{}";
    }
}
