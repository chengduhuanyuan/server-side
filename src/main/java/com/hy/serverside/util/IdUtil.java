package com.hy.serverside.util;

import java.util.UUID;

/**
 * @ClassName: IdUtil
 * @Description: TODO 生成订单id
 * @Author: Kaiser
 * @Date: 2019/4/22 16:16
 * @Version: 1.0
 */
public class IdUtil {
    private static int machineId = 1;
    private static int hashCodeV = UUID.randomUUID().toString().hashCode();
    /**
     *  获取订单ID
     * @return
     */
    public static String getOrderId(){
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        return machineId + String.format("%015d",hashCodeV);
    }

    /**
     *  生成唯一id
     * @return
     */
    public static String getId(){
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        return machineId + String.format("%010d",hashCodeV);
    }
}
