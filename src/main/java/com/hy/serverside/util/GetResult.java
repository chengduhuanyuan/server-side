package com.hy.serverside.util;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: GetResult
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/10 11:16
 * @Version: 1.0
 */
public class GetResult {
    public static JsonData result(List list){
        if (list.isEmpty()){
            return new JsonData(null,"fail",false);
        }
        return new JsonData(list,"success",true);
    }

    public static JsonData payResult(Map<String,String> map){
        if (Constant.TRADE.equals(map.get(Constant.RETURN_CODE))){
            return new JsonData(map,"success",true);
        }
        return new JsonData(map,"fail",false);
    }
}
