package com.hy.serverside.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.serverside.entity.Product;
import com.hy.serverside.entity.ProductDetail;
import com.hy.serverside.mapper.ProductMapper;
import com.hy.serverside.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ProductServiceImpl
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/19 18:20
 * @Version: 1.0
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Override
    public int Favorite(String openid,String goodid) {
        String favorite = baseMapper.Favorite(openid,goodid);
        if (favorite==null){
            return 0;
        }
            return 1;

    }

    @Override
    public int addFavorite(String openId, String goodsId) {
        boolean b=baseMapper.addFavorite(openId,goodsId);
        if (b){
            return 0;
        }
        return 1;
    }

    @Override
    public List<Product> getCateAll(int page, int size, String category) {
        page=(page-1)*size;
        List<Product> products=baseMapper.getCateAll(page,size,Integer.parseInt(category));
        return products;
    }

    @Override
    public int getCateSize(String category) {
        int s=baseMapper.getCateSize(Integer.parseInt(category));
        return s;
    }

    @Override
    public List<Product> searchkeyword(String page, String size, String searchKeyWords,String category) {

        int p=Integer.parseInt(page);
        int s=Integer.parseInt(size);
        List<Product> productlsit=baseMapper.searchkeyword((p-1)*s,s,searchKeyWords,Integer.parseInt(category));
        return productlsit;
    }

    @Override
    public ProductDetail getProductDetailById(String id) {
        return baseMapper.getProductDetailById(id);
    }
}
