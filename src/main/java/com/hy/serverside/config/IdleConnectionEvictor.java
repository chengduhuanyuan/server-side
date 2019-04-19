package com.hy.serverside.config;

import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: IdleConnectionEvictor
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/4/17 16:11
 * @Version: 1.0
 */
@Component
public class IdleConnectionEvictor extends Thread {
    @Autowired
    private HttpClientConnectionManager connectionManager;
    private volatile boolean shutdown;
    public IdleConnectionEvictor(){
        super();
        super.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown){
                synchronized (this) {
                    wait(5000);
                    //关闭连接
                    connectionManager.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex){

        }
    }

    /**
     *  关闭清理无效连接的线程
     */
    public void shutdown(){
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
