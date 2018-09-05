package com.answern.concurrency.concurrency.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/4 15:19]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Service
public class ExecuteAsyncTaskService {


    @Async("asyncServiceExecutor")
    public  void executeAsyncTask(Integer n){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步任务执行："+n);
    }
}
