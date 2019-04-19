package com.hy.serverside.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName: HttpClinetConfig
 * @Description: TODO http连接配置
 * @Author: Kaiser
 * @Date: 2019/4/17 15:16
 * @Version: 1.0
 */
@Configuration
@ComponentScan("com.hy.serverside.config")
@PropertySource("classpath:application-httpclient.yml")
public class HttpClinetConfig {
    @Value("${http.maxTotal}")
    private int maxTotal;
    @Value("${http.defaultMaxPerRoute}")
    private int defaultMaxPerRoute;
    @Value("${http.connectTimeout}")
    private int connectTimeout;
    @Value("${http.connectionRequestTimeout}")
    private int connectionRequestTimeout;
    @Value("${http.socketTimeout}")
    private int socketTimeout;
    @Value("${http.validateAfterInactivity}")
    private int validateAfterInactivity;

    /**
     *  实例化连接池管理器，设置最大链接数，并发连接
     * @return
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManage(){
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        httpClientConnectionManager.setMaxTotal(maxTotal);
        //并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        httpClientConnectionManager.setValidateAfterInactivity(validateAfterInactivity);
        return httpClientConnectionManager;
    }

    /**
     * 实例化链接池，设置链接池管理器
     * @param httpClientConnectionManager 需要形式参数注入上面的连接池管理
     * @return
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        return httpClientBuilder;
    }

    /**
     *  注入连接池，用于获取httpClient
     * @param httpClientBuilder
     * @return
     */
    @Bean()
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder){
        return httpClientBuilder.build();
    }

    /**
     * Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     * @return
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder(){
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout);
    }

    /**
     *  使用builder构建一个RequestConfig对象
     * @param builder
     * @return
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder){
        return builder.build();
    }
}
