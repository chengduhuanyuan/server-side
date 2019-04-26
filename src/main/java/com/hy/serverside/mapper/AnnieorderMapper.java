package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.entity.Annieorder;

import java.util.List;

/**
 * @ClassName: AnnieorderMapper
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:00
 * @Version: 1.0
 */
public interface AnnieorderMapper extends BaseMapper<Annieorder> {
    int getOrderSize(String openid);

    List<Annieorder> getOrderAll(String openid, int orderStatus, int page, int size);

    List<Annieorder> getOrderById(String openid);

    int delOrder(String orderNo);

    int orderConfirm(String orderNo);
}
