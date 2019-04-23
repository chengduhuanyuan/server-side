package com.hy.serverside.util;


import java.text.*;
import java.util.Calendar;
import java.util.UUID;

/**
 * @ClassName: IdUtil
 * @Description: TODO 生成订单id
 * @Author: Kaiser
 * @Date: 2019/4/22 16:16
 * @Version: 1.0
 */
public class IdUtil {
    private static volatile IdUtil instance;
    private IdUtil() {}
    public static IdUtil getInstance(){
        if (instance == null){
            synchronized (IdUtil.class){
                if (instance == null) {
                    instance = new IdUtil();
                }
            }
        }
        return instance;
    }

    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /** 时间：精确到秒 */
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");

    private final static NumberFormat numberFormat = new DecimalFormat("00000");

    private static int seq = 0;

    private static final int MAX = 99999;

    public synchronized String generateOrderNo() {

        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

        numberFormat.format(seq, sb, HELPER_POSITION);

        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }

        return sb.toString();
    }

   public String generateIdNo(){
       int hashCodeV = UUID.randomUUID().toString().hashCode();
       if (hashCodeV < 0) {
           hashCodeV = -hashCodeV;
       }
       return String.format("%010d",hashCodeV);
   }

}
