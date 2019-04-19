package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.User;

/**
 * @ClassName: IUserService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 16:46
 * @Version: 1.0
 */
public interface IUserService extends IService<User> {
    User getTest(Integer id);
}
