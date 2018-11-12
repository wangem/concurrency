package com.answern.concurrency.concurrency.distributed;

import com.answern.logback.config.aop.ControllerLog;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求名称:分布式事物<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/28 11:14]  <br/>
 * 版本:[v1.0]   <br/>
 */
@RestController
@RequestMapping("/dis")
public class DisController {

    @Autowired
    DisService disService;

    @ControllerLog
    @RequestMapping("index")
    public String index(){
        //String index = disService.index();
        //System.out.println(index);
        return "ok";
    }
}
