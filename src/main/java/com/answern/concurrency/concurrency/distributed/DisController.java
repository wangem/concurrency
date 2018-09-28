package com.answern.concurrency.concurrency.distributed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/28 11:14]  <br/>
 * 版本:[v1.0]   <br/>
 */
@RestController
public class DisController {

    @Autowired
    DisService disService;

    public String index(){
        String index = disService.index();
        System.out.println(index);
        return "ok";
    }
}
