package com.hy.serverside.controller;


import com.hy.serverside.entity.Images;
import com.hy.serverside.entity.Product;
import com.hy.serverside.entity.ProductDetail;
import com.hy.serverside.service.IImagesService;
import com.hy.serverside.service.IProductService;
import com.hy.serverside.util.GetResult;
import com.hy.serverside.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  获取所有商品列表(公众号前期使用)【暂时】
     * @return
     */
    @CrossOrigin
    @GetMapping("/getStepProduct")
    public JsonData getProduct(){
        List<Product> list = iProductService.list();
        return GetResult.result(list);
    }

    /**
     *  根据ID获取商品详情（公众号前期使用）【暂时】
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping("/getStepProductById")
    public JsonData getProductById(String id){
        ProductDetail product = iProductService.getProductDetailById(id);
        if (product != null) {
            return new JsonData(product,"success",true);
        }
        return new JsonData(null,"fail",false);
    }

    /***
    *@Description 分页查看所有商品
    *@Param [page, size]
    *@Return java.util.Map<java.lang.String,java.lang.Object>
    *@Author 杨席杰
    *@Date 2019/4/23
    *@Time 11:10
    */
    @GetMapping("/getAll")
    @ResponseBody
    public Map<String,Object> getAll(String page,String size,String category){
        if(category==null){
            category="0";
        }
        List<Product> products=iProductService.getCateAll(Integer.parseInt(page),Integer.parseInt(size),category);
        int length=iProductService.getCateSize(category);
        Map<String,Object> map=new HashMap<>();
        int total;
        if(length%10>0){
            total=length/10+1;
        }else {
            total=length/10;
        }
        map.put("reason","");
        map.put("code",0);
        map.put("page_total",total);
        map.put("list",products);
        return map;
    }
    
    /***
    *@Description 查看商品详细信息
    *@Param [id]
    *@Return java.util.Map<java.lang.String,java.lang.Object>
    *@Author 杨席杰
    *@Date 2019/4/23
    *@Time 11:11
    */
    @GetMapping("/getById")
    @ResponseBody
    public Map<String,Object> getbyid(String id){
        Product byId = iProductService.getById(id);
        List<Images> imgs = iImagesService.getAll(id);
        byId.setDetailInfo("<p><img src=\"http://sujiefs.com/upload/images/20171229/201712291153338944216.jpg\" title=\"201712291153338944216.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291153339407769.jpg\" title=\"201712291153339407769.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155321086314.jpg\" title=\"201712291155321086314.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155360982127.jpg\" title=\"201712291155360982127.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155419222126.jpg\" title=\"201712291155419222126.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155458677195.jpg\" title=\"201712291155458677195.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155512880017.jpg\" title=\"201712291155512880017.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155549745474.jpg\" title=\"201712291155549745474.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291155587838145.jpg\" title=\"201712291155587838145.jpg\"/></p><p><img src=\"http://sujiefs.com/upload/images/20171229/201712291156043264507.jpg\" title=\"201712291156043264507.jpg\"/></p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<br/></p><p><br/><br/></p><p><br/></p>");
        Map<String,Object> map=new HashMap<>();
        map.put("data",byId);
        map.put("code",0);
        map.put("msg","");
//        能否点击购买   1是可以  0是不行
        map.put("validDate",1);
        return map;
    }

    /***
    *@Description 判断该商品是否被收藏
    *@Param [openId, goodsId]
    *@Return java.util.Map<java.lang.String,java.lang.Object>
    *@Author 杨席杰
    *@Date 2019/4/23
    *@Time 8:57
    */
    @GetMapping("/goodsFavorite")
    @ResponseBody
    public Map<String,Object> goodfavorite(String openId,String goodsId){
        Map<String,Object> map=new HashMap<>();
        int f;
        try {
            f=iProductService.Favorite(openId,goodsId);

        }catch (Exception e){
            map.put("code",-1);
        }
        map.put("reason","");
        return map;
    }
    @GetMapping("/addFavorite")
    @ResponseBody
    public Map<String,Object> addFavorite(String openId,String goodsId){
       int f= iProductService.addFavorite(openId,goodsId);
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("result","");
        return map;
    }
    @GetMapping("/searchkeyword")
    public Map<String,Object> searchkeyword(String searchKeyWords,String page,String size,String skuval){
        String c=skuval;
        if(skuval==null||skuval==""){
            skuval="0";
            c="-1";
        }
        System.out.println(c);
        List<Product> productList=iProductService.searchkeyword(page,size,searchKeyWords,c);
        Map<String,Object> map=new HashMap<>();
        int length=iProductService.getCateSize(skuval);
        int total;
        if(length%10>0){
            total=length/10+1;
        }else {
            total=length/10;
        }
        map.put("list",productList);
        map.put("code",0);
        map.put("pageNum",1);
        map.put("pageSize",10);
        map.put("page_total",total);
        map.put("reason","");
        map.put("totalCount",0);
        return map;
    }
}
