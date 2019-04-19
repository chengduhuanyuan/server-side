package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Images;
import com.hy.serverside.mapper.ImagesMapper;
import com.hy.serverside.service.IImagesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ImagesServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:24
 * @Version: 1.0
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements IImagesService {
    @Override
    public List<Images> getAll(Integer id) {
        return baseMapper.getImages(id);
    }
}
