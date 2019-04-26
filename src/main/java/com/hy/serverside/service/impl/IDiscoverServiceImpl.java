package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Discover;
import com.hy.serverside.mapper.DiscoverMapper;
import com.hy.serverside.service.IDiscoverService;
import org.springframework.stereotype.Service;

/**
 * @author 杨席杰
 * @date 2019/4/26 14:11
 */
@Service
public class IDiscoverServiceImpl extends ServiceImpl<DiscoverMapper, Discover> implements IDiscoverService {
}
