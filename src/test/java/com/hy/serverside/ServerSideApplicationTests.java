package com.hy.serverside;

import com.hy.serverside.mapper.ProductMapper;
import com.hy.serverside.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerSideApplicationTests {
    ProductMapper productMapper;
    @Test
    public void contextLoads() {
        System.out.println(11%10);
    }

}
