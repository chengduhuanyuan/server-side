package com.hy.serverside.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

/**
 * @ClassName: WebConfig
 * @Description: TODO 序列化配置
 * @Author: Kaiser
 * @Date: 2019/4/16 11:03
 * @Version: 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                // 输出key时使用双引号
                QuoteFieldNames,
                // 否输出值为null的字段
                WriteMapNullValue,
                //数值字段如果为null,输出为0,而非null
                WriteNullNumberAsZero,
                //List字段如果为null,输出为[],而非null
                WriteNullListAsEmpty,
                //字符类型字段如果为null,输出为"",而非null
                WriteNullStringAsEmpty,
                /*//Boolean字段如果为null,输出为false,而非null
                WriteNullBooleanAsFalse,
                // null String不输出
                WriteNullStringAsEmpty,
                //null String也要输出
                WriteMapNullValue,
                //Date的日期转换器
                WriteDateUseDateFormat,*/
                //禁止循环引用
                DisableCircularReferenceDetect
        );
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(list);
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }



}
