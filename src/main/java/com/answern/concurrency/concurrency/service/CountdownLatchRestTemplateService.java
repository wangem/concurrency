package com.answern.concurrency.concurrency.service;

import com.answern.concurrency.concurrency.Dao.RunThreadServerImp;
import com.answern.concurrency.concurrency.base.RunThreadServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/8 15:20]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Service
public class CountdownLatchRestTemplateService {

    @Autowired
    RestTemplate restTemplate ;


    @Autowired
    Executor asyncServiceExecutor;

    public   void resttemplate(int countNumber,String url){
       // int countNumber = 10;
        Map producesMap = new HashMap();
        producesMap.put("url",url);
        producesMap.put("i",2);
        producesMap.put("restTemplate",restTemplate);
        //裁判员鸣枪信号
        CountDownLatch begin = new CountDownLatch(1);
        //10名参赛选手结束信号
        CountDownLatch end = new CountDownLatch(countNumber);


        RunThreadServer runThreadServer = new RunThreadServerImp(begin,end,producesMap);


       // Executor executor = Executors.newFixedThreadPool(countNumber);

            for(int i =0;i<countNumber;i++)
            {
                asyncServiceExecutor.execute(runThreadServer);
            }
            begin.countDown();//裁判员鸣枪了
            try {
                end.await();//等待10个参赛选手都跑完100米
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }



}
