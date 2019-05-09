package com.hy.weixin.entity;

/**
 * @Auther: 杨席杰
 * @Date: 2018/12/25 15:14
 * @Description:
 */
public class Scene {
    private int scene_id;
    private String scene_str;

    public int getScene_id() {
        return scene_id;
    }

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public String getScene_str() {
        return scene_str;
    }

    public void setScene_str(String scene_str) {
        this.scene_str = scene_str;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "scene_id=" + scene_id +
                ", scene_str='" + scene_str + '\'' +
                '}';
    }
}
