package com.answern.concurrency.concurrency.customTags.customservice;

import com.answern.concurrency.concurrency.customTags.customservice.annotation.*;

/**
 * 需求名称: 这个功能是没有实现的
 *  自定义spring mvc的注解<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/26 21:16]  <br/>
 * 版本:[v1.0]   <br/>
 */
@MyController
@MyRequestMapping(name = "/ann")
public class AnController {

    @MyAutoWrite
    private AnService anService;

    @MyRequestMapping(name = "init")
    public String init(@MyRequestParam("name") String name){
        System.out.println("is init insert ");
        System.out.println(anService.thisInit());
        return name;
    }
}
