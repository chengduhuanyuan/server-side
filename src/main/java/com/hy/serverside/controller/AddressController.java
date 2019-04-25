package com.hy.serverside.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.serverside.entity.Address;
import com.hy.serverside.service.IAddressService;
import com.hy.serverside.util.IdUtil;
import com.hy.serverside.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: AddressController
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/25 10:27
 * @Version: 1.0
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    /**
     *  保存收货地址
     * @param openId
     * @param mobile
     * @param receiverName
     * @param addressDetail
     * @param province
     * @param city
     * @param area
     * @param isDef
     * @return
     */
    @GetMapping("/saveAddress")
    public JsonData saveAddress(String openId,String mobile,String receiverName,String addressDetail,
                                String province,String city,String area,int isDef){
        boolean isDefault = isDef == 1;
        String idNo = IdUtil.getInstance().generateIdNo();
        Address address = new Address(idNo,openId,mobile,receiverName,province,city,area,addressDetail,isDefault);
        boolean save = addressService.save(address);
        if (save) {
            addressService.updateDefault(idNo,openId);
            return new JsonData(null,"添加成功",save);
        }
        return new JsonData(null,"添加失败",save);
    }

    /**
     *  查询收货地址列表
     * @param openId
     * @return
     */
    @GetMapping("/getAddress")
    public JsonData getAddress(String openId){
        List<Address> addressList = addressService.list(new QueryWrapper<Address>().eq("openid", openId));
        if (!addressList.isEmpty()){
            if (addressList.size() == 1) {
                Address add = addressList.get(0);
                add.setDefault(true);
            }
            int num = 0;
            for (Address add:addressList) {
                if (!add.isDefault()){
                    num++;
                }
            }
            if (num == addressList.size()){
                Address add = addressList.get(0);
                add.setDefault(true);
            }
            return new JsonData(addressList,"成功获取",true);
        }
        return new JsonData(null,"您未添加地址",false);
    }

    /**
     *  通过id查询收货地址
     * @param id
     * @return
     */
    @GetMapping("/receiverInfoById")
    public JsonData getAddressById(String id){
        Address address = addressService.getById(id);
        if (address != null){
            return new JsonData(address,"success",true);
        }
        return new JsonData(null,"fail",false);
    }

    /**
     *  根据ID删除地址
     * @param id
     * @return
     */
    @GetMapping("/delAddressById")
    public JsonData delAddressById(String id){
        boolean remove = addressService.removeById(id);
        if (remove){
            return new JsonData(null,"success",true);
        }
        return new JsonData(null,"fail",false);
    }

    @GetMapping("/updateAddress")
    public JsonData updateAddressById(String id,String openId,String mobile,String receiverName,String addressDetail,
                                      String province,String city,String area,int isDef){
        boolean isDefault = isDef ==1;
        Address address = new Address(id,openId,mobile,receiverName,province,city,area,addressDetail,isDefault);
        boolean updateById = addressService.updateById(address);
        if (updateById) {
            addressService.updateDefault(id,openId);
            return new JsonData(null,"更新成功",updateById);
        }
        return new JsonData(null,"更新失败",updateById);
    }
}
