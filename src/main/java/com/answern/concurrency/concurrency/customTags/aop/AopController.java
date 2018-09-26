package com.answern.concurrency.concurrency.customTags.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/18 8:10]  <br/>
 * 版本:[v1.0]   <br/>
 */
@RestController
@RequestMapping("/aop")
public class AopController {

    @RequestMapping("index")
    @AopDosomething(name = "#name",key = "key")
    public String aopIndex(String name){
        System.out.println("this is aop index");
        return "ok"+name;
    }
}
