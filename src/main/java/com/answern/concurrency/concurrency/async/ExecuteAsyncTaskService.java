package com.answern.concurrency.concurrency.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

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


    @Async("asyncServiceExecutors")
    public  void executeAsyncTask(Integer n){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步任务执行："+n);
    }

    @Async("asyncServiceExecutors")
    public Future<String> executeAsyncTaskReturn(Integer n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步任务执行返回："+n);
        return new AsyncResult<>("任务完成");
    }
}
