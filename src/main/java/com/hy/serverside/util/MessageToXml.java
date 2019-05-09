package com.hy.serverside.util;

import com.hy.serverside.entity.TextMessage;
import com.sun.org.apache.xerces.internal.xs.XSTerm;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.oxm.xstream.XStreamMarshaller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨席杰
 * @date 2019/5/9 16:48
 */
public class MessageToXml {
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

        Map<String,String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        //获取根节点
        Element root = doc.getRootElement();

        List<Element> list = root.elements();

        for(Element e : list){
            map.put(e.getName(), e.getText());
        }
        ins.close();

        return map;
    }

    public static String textMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }


}
