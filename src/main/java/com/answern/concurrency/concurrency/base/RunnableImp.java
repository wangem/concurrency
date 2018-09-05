package com.answern.concurrency.concurrency.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/3 17:01]  <br/>
 * 版本:[v1.0]   <br/>
 */
public class RunnableImp implements Runnable {


    private int councurrencyCount = 200;
    private Thread t;
    private String threadName;

    private String url;

    RunnableImp(String threadName,String url) {
        this.threadName = threadName;
        this.url = url;
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

}