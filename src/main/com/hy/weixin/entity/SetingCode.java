package com.hy.weixin.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 杨席杰
 * @Date: 2018/12/25 14:57
 * @Description:
 */
public class SetingCode {
    private String expire_seconds;
    private String action_name;
    private Map<Object,Object> action_info=new HashMap<>();

    public String getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(String expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public Map<Object, Object> getAction_info() {
        return action_info;
    }

    public void setAction_info(Map<Object, Object> action_info) {
        this.action_info = action_info;
    }

    @Override
    public String toString() {
        return "SetingCode{" +
                "expire_seconds='" + expire_seconds + '\'' +
                ", action_name='" + action_name + '\'' +
                ", action_info=" + action_info +
                '}';
    }
}
