package com.hy.serverside.controller;


import com.hy.serverside.entity.Images;
import com.hy.serverside.entity.Product;
import com.hy.serverside.service.IImagesService;
import com.hy.serverside.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kaiser
 * @since 2019-04-15
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IImagesService iImagesService;



    @GetMapping("/getAll")
    @ResponseBody
    public Map<String,Object> getAll(){
        List<Product> list = iProductService.list();
        Map<String,Object> map=new HashMap<>();
        map.put("reason","");
        map.put("code",0);
        map.put("page_total",list.size());
        map.put("list",list);
        return map;
    }

    @GetMapping("/getById")
    @ResponseBody
    public Map<String,Object> getbyid(Integer id){
        Product byId = iProductService.getById(id);
        List<Images> imgs = iImagesService.getAll(id);
        byId.setImages(imgs);
        byId.setDetailInfo("<p><img src=\"http://sujiefs.com/upload/images/20171229/201712291153338944216.jpg\" title=\"201712291153338944216.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291153339407769.jpg\" title=\"201712291153339407769.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155321086314.jpg\" title=\"201712291155321086314.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155360982127.jpg\" title=\"201712291155360982127.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155419222126.jpg\" title=\"201712291155419222126.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155458677195.jpg\" title=\"201712291155458677195.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155512880017.jpg\" title=\"201712291155512880017.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155549745474.jpg\" title=\"201712291155549745474.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155587838145.jpg\" title=\"201712291155587838145.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291156043264507.jpg\" title=\"201712291156043264507.jpg\"/></p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<br/></p><p><br/><br/></p><p><br/></p>");
        Map<String,Object> map=new HashMap<>();
        map.put("data",byId);
        map.put("code",0);
        map.put("msg","");
//        能否点击购买   1是可以  0是不行
        map.put("validDate",1);
        return map;
    }
}
