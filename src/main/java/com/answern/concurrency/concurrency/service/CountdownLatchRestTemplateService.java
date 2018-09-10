package com.answern.concurrency.concurrency.service;

import com.answern.concurrency.concurrency.Dao.RunThreadServerImp;
import com.answern.concurrency.concurrency.base.RunThreadServer;
import org.springframework.stereotype.Service;

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

    public static void resttemplate(){
        int countNumber = 10;
        //裁判员鸣枪信号
        CountDownLatch begin = new CountDownLatch(1);
        //10名参赛选手结束信号
        CountDownLatch end = new CountDownLatch(countNumber);


        RunThreadServer runThreadServer = new RunThreadServerImp("restTemplate",begin,end);

        Executor executor = Executors.newFixedThreadPool(countNumber);

            for(int i =0;i<countNumber;i++)
            {
                System.out.println("getCount="+end.getCount());
                executor.execute(runThreadServer);

                System.out.println("over");
            }
            System.out.println("countDown");

            begin.countDown();//裁判员鸣枪了
            try {
                end.await();//等待10个参赛选手都跑完100米
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    public static void main(String[] args) {
        resttemplate();
    }

}
