package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.Coupon;
import com.hy.serverside.entity.Relation;
import com.hy.serverside.entity.User;

/**
 * @ClassName: IUserService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 16:46
 * @Version: 1.0
 */
public interface IUserService extends IService<User> {
    User getTest(String id);

    boolean addUser(User user);

    boolean saveSellto(String parentName, String subclass);

    Relation getSuperior(String superior, String oneself);

    String getParentName(String subclassName);

    User getByopenId(String openId);

    void svaeNode(String name);

    Coupon getCoupon(String userId);

    void saveCoupon(String openId);
}
