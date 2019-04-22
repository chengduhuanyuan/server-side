package com.hy.serverside.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.serverside.entity.Images;

import java.util.List;

/**
 * @ClassName: ImagesMapper
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:20
 * @Version: 1.0
 */
public interface ImagesMapper extends BaseMapper<Images> {
    List<Images> getImages(String id);
}
