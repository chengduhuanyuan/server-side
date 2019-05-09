package com.hy.serverside.util;

import com.hy.serverside.entity.ReceiveXmlModel;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * @author 杨席杰
 * @date 2019/5/9 15:02
 */
public class ReceiveXmlProcess {
    public static ReceiveXmlModel getMsgEntity(String strXml) {
        ReceiveXmlModel msg = null;
        try {
            if (strXml.length() <= 0 || strXml == null)
                return null;
            // 将字符串转化为XML文档对象
            Document document = DocumentHelper.parseText(strXml);
            // 获得文档的根节点
            Element root = document.getRootElement();
            // 遍历根节点下所有子节点
            Iterator<?> iter = root.elementIterator();
            // 遍历所有结点
            msg = new ReceiveXmlModel();
            //利用反射机制，调用set方法
            //获取该实体的元类型
            Class<?> c = Class.forName("com.sniper.weixin.model.ReceiveXmlModel");
            msg = (ReceiveXmlModel)c.newInstance();//创建这个实体的对象
            while(iter.hasNext()){
                Element ele = (Element)iter.next();
                //获取set方法中的参数字段（实体类的属性）
                Field field = c.getDeclaredField(ele.getName());
                //获取set方法，field.getType())获取它的参数数据类型
                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());
                //调用set方法
                method.invoke(msg, ele.getText());
            }
        } catch (Exception e) {
            System.out.println("xml 格式异常: "+ strXml);
            e.printStackTrace();
        }
        return msg;
    }

//       String token="nimei";//这里填基本配置中的token
//        String jiami="";
//        try {
//            jiami= SHA1.getSHA1(token, timestamp, nonce,"");//这里是对三个参数进行加密
//        } catch (AesException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println("加密"+jiami);
//        System.out.println("本身"+signature);
//        if(jiami.equals(signature)){
//            return echostr;
//        }else{
//            return "";
//        }
}
