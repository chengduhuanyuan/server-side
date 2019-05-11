package com.hy.serverside.controller;

import com.hy.serverside.entity.Scene;
import com.hy.serverside.entity.SetingCode;
import com.hy.serverside.entity.Ticket;
import com.hy.serverside.util.*;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨席杰
 * @date 2019/5/9 11:00
 */
@RestController
@RequestMapping("/wexin")
public class WeXinController {
//配置验证
    @GetMapping("/verifyToken")
    public String verifyToken(String signature,String timestamp,String nonce,String echostr){
        String token="nimei";//这里填基本配置中的token
        String jiami="";
        try {
            jiami= SHA1.getSHA1(token, timestamp, nonce,"");//这里是对三个参数进行加密
        } catch (AesException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(jiami.equals(signature)){
            return echostr;
        }else{
            return "";
        }
    }
//    分销二维码
    @ResponseBody
    @GetMapping("/getSaleCode")
    public JsonData getSaleCode(){
        try {
            Scene scene = new Scene();
            //场景值
            scene.setScene_str("login");
            SetingCode data = new SetingCode();
            //设置二维码有效时间
            data.setExpire_seconds("600");
            data.setAction_name("QR_STR_SCENE");
            Map<Object,Object> m=new HashMap<>();
            m.put("scene",scene);
            data.setAction_info(m);
            //将对象转换为json
            String s = JSONObject.fromObject(data).toString();
            //获取登陆二维码
            String code = getQrCode.getQrCode(s);
            //将字符串转换json
            JSONObject jsonObject = JSONObject.fromObject(code);
            //json转换对象
            Ticket o = (Ticket) JSONObject.toBean(jsonObject, Ticket.class);
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("Ticket",o);
            return new JsonData(map,"success",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonData(null,"fail",false);
        }
    }


//接收微信公众号消息
    @PostMapping("/verifyToken")
    public void getWeXml(HttpServletRequest request, HttpServletResponse response)throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, String> stringStringMap = MessageToXml.xmlToMap(request);

    }
}
