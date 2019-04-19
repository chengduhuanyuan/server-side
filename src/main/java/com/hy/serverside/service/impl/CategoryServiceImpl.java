package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Category;
import com.hy.serverside.mapper.CategoryMapper;
import com.hy.serverside.service.ICategoryService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:16
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
}
