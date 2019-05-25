package com.hy.serverside;

import com.github.wxpay.sdk.WXPay;
import com.hy.serverside.config.WeChatPayConfig;
import com.hy.serverside.mapper.ProductMapper;
import com.hy.serverside.service.impl.ProductServiceImpl;
import com.hy.serverside.util.Constant;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerSideApplicationTests {
    @Test
    public void contextLoads() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }

    @Test
    public void hello(){
            Runnable myRunnable = new Runnable(){
                public void run(){
                    System.out.println("Runnable running");
                }
            };
            Thread thread = new Thread(myRunnable);
            thread.run();
            System.out.println("aa");

    }
    @Test
    public void pa(){
//        MyConfig config = new MyConfig();
        WeChatPayConfig config = new WeChatPayConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "2016090910595900000012");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "171.214.136.238");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", Constant.TRADE_TYPE);
        data.put("openid","oVgkK5wdVxrGihMkxQiiDemyTXSo");

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
