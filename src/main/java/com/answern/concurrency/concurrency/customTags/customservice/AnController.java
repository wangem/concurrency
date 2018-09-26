package com.answern.concurrency.concurrency.customTags.customservice;

import com.answern.concurrency.concurrency.customTags.customservice.annotation.*;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
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
        System.out.println(anService.thisInit());
        System.out.println(anService.thisInit());
        return name;
    }
}
