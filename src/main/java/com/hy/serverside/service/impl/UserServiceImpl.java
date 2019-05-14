package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Relation;
import com.hy.serverside.entity.User;
import com.hy.serverside.mapper.UserMapper;
import com.hy.serverside.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 16:49
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public User getTest(String id) {
        return baseMapper.getUserTest(id);
    }

    @Override
    public boolean addUser(User user) {

        boolean result = baseMapper.addUser(user);
        return false;
    }

    @Override
    public boolean saveSellto(String parentName, String subclassName) {
        String parentName1 = baseMapper.getParentName(subclassName);
        boolean b=false;
        if(parentName1==null||parentName==""){
            b=false;
        }else {
            baseMapper.saveSellto(parentName,subclassName);

        }
        return b;
    }

    @Override
    public Relation getSuperior(String superior, String oneself) {
        Relation relation = baseMapper.getSuperior(superior, oneself);

        return relation;
    }

    @Override
    public String getParentName(String subclassName) {
        String parentName=baseMapper.getParentName(subclassName);
        return null;
    }
}
