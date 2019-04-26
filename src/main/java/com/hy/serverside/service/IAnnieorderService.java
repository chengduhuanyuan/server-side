package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.Annieorder;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IAnnieorderService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:11
 * @Version: 1.0
 */
public interface IAnnieorderService extends IService<Annieorder> {
    int getOrderSize(String openid);

    List<Annieorder> getOrderAll(String openid, int orderStatus, int page, int size);

    Map<String,Object> getOrderById(String openid);
}
