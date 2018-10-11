package com.answern.concurrency.concurrency.redisOrderNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/10/11 20:24]  <br/>
 * 版本:[v1.0]   <br/>
 */
@RestController
@RequestMapping("orderNumber")
public class Controller {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("index")
    public String index() {

        RedisAtomicLong redisAtomicLong = new RedisAtomicLong("someKey", redisTemplate.getConnectionFactory());
        // 第一次，设置初始值
        long original = 0L;
        // 获取 code 值
        original = redisAtomicLong.get();
        System.out.println("*****************original:" + original);
// 第一次，设置初始值
        if (original == 0L) {
            redisAtomicLong.set(5L);
        }
//获得加1后的值
        long now = redisAtomicLong.incrementAndGet();
        System.out.println("*****************now:" + now);
        return "ok";
    }
}
