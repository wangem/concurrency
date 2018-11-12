package com.answern.concurrency.concurrency.customTags.prox;

import com.answern.logback.config.aop.ControllerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/26 16:59]  <br/>
 * 版本:[v1.0]   <br/>
 */
@RestController
@RequestMapping("/prox")
public class ProxController {

    @ProxClient(name = "indexDo")
    @RequestMapping("index")
    public String index(){
        System.out.println("进入indexDo");
        return "ok";
    }


    @Autowired
    test11 test11;
    @RequestMapping("index1")
    public String index11(){

        System.out.println("进入indexDo");
        test11.test();
        return "ok";
    }
}
