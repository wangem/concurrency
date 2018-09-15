package com.answern.concurrency.concurrency.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/15 17:03]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Component
public class TastDemo {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private int count=0;

    /**
     * demo 1
     */
    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        System.out.println("[" + Thread.currentThread().getName() + "]" + "this is scheduler task runing  "+(count++));
    }



    /**
     * drmo 2
     */
    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("[" + Thread.currentThread().getName() + "]" + "现在时间：" + dateFormat.format(new Date()));
    }
}
