package com.answern.concurrency.concurrency.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 需求名称:开启对异步任务的支持
 * 类描述:[异步调用配置]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/4 14:29]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Configuration
@EnableAsync
public class ThreadAsyncConfigurer  implements AsyncConfigurer {
    @Bean
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        //设置核心线程数
        threadPool.setCorePoolSize(10);
        //设置最大线程数
        threadPool.setMaxPoolSize(100);
        //线程池所使用的缓冲队列
        threadPool.setQueueCapacity(10);
        //等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(60);
        //  线程名称前缀
        threadPool.setThreadNamePrefix("concurrencyAsync-");
        // 初始化线程
        threadPool.initialize();
        return threadPool;
    }
}