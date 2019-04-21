package com.hy.serverside.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.serverside.entity.Images;

import java.util.List;

/**
 * @ClassName: IImagesService
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 17:23
 * @Version: 1.0
 */
public interface IImagesService extends IService<Images> {

    List<Images> getAll(Integer id);
}
