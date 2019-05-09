package com.hy.serverside.util;

import com.hy.serverside.service.IHttpService;
import com.hy.serverside.service.impl.HttpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @author 杨席杰
 * @date 2019/5/9 18:02
 */

public class getQrCode {
    public static void getQrCode() throws Exception {

        IHttpService iHttpService=new HttpServiceImpl();
        HashMap<String,Object> map=new HashMap<>();
        map.put("expire_seconds",1);
        map.put("action_name","");
        map.put("action_info","");
        iHttpService.doGet(Constant.CODE_URL,map);

    }
}
