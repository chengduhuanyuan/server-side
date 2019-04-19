package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Property;
import com.hy.serverside.mapper.PropertyMapper;
import com.hy.serverside.service.IPropertyService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: PropertyServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:22
 * @Version: 1.0
 */
@Service
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property> implements IPropertyService {
}
