package com.hy.serverside.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: TimeUtil
 * @Description: TODO 时间工具类
 * @Author: Kaiser
 * @Date: 2019/4/22 11:01
 * @Version: 1.0
 */
public class TimeUtil {
    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
