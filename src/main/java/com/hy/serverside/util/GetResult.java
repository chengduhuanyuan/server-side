package com.hy.serverside.util;

import java.util.List;

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
}
