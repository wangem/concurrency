package com.answern.concurrency.concurrency.countdownLatch;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求名称:
 * 类描述:[调用异步方法测试]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/3 16:04]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Api(value = "发令枪并发执行", description = "发令枪并发执行")
@RestController
@RequestMapping("/countdownLatch")
public class CountdownLatchController {

    private final static Logger logger = LoggerFactory.getLogger(CountdownLatchController.class);


    @Autowired
    CountdownLatchRestTemplateService countdownLatchRestTemplateService;

    /**
     * 调用异步方法
     * @return
     */
    @ApiOperation(value = "并发调用方法", notes = "并发调用方法" )
    @RequestMapping(value = "index" ,method = RequestMethod.GET)
    public String countdownLatch(String url,int countNumber){
        //"http://localhost:8080/resttemplate/main"
        countdownLatchRestTemplateService.resttemplate(countNumber,url);
        return "并发";
    }


  

}
