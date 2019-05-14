package com.hy.serverside;

import com.hy.serverside.mapper.ProductMapper;
import com.hy.serverside.service.impl.ProductServiceImpl;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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

}
