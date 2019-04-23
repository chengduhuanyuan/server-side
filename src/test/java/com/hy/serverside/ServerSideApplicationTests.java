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
        boolean b = productMapper.addFavorite("oo3H-4_UiXcMu9baT26xsLA7KQUQ", "4");
//        int i = service.addFavorite("oo3H-4_UiXcMu9baT26xsLA7KQUQ", "4");
        System.out.println(b);
    }

}
