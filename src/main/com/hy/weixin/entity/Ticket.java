package com.hy.weixin.entity;

/**
 * @Auther: 杨席杰
 * @Date: 2018/12/25 16:02
 * @Description:
 */
public class Ticket {
    private String ticket;
    private String expire_seconds;
    private String url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(String expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket='" + ticket + '\'' +
                ", expire_seconds='" + expire_seconds + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
