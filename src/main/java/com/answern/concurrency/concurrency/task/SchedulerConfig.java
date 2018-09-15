package com.answern.concurrency.concurrency.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 需求名称: 将task线程设置为多线程服务<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/15 17:10]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //线程池大小
        scheduler.setPoolSize(10);
        //线程名字前缀
        scheduler.setThreadNamePrefix("spring-task-thread");
        return scheduler;
    }
}
