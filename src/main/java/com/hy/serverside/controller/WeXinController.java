package com.hy.serverside.controller;

import com.hy.serverside.util.AesException;
import com.hy.serverside.util.JsonData;
import com.hy.serverside.util.MessageToXml;
import com.hy.serverside.util.SHA1;
import org.apache.catalina.connector.Response;
import org.apache.http.HttpResponse;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
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
//接收微信公众号消息
    @PostMapping("/verifyToken")
    public void getWeXml(HttpServletRequest request, HttpServletResponse response)throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, String> stringStringMap = MessageToXml.xmlToMap(request);

    }
}
