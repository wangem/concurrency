package com.answern.concurrency.concurrency.controller;

import com.answern.concurrency.concurrency.service.ExecuteAsyncTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
@RequestMapping("countdownLatch")
public class CountdownLatchController {

    private final static Logger logger = LoggerFactory.getLogger(CountdownLatchController.class);


    /**
     * 调用异步方法
     * @return
     */
    @ApiOperation(value = "调用异步方法", notes = "调用异步方法")
    @RequestMapping(value = "countdownLatch" ,method = RequestMethod.GET)
    public String countdownLatch(){

        return "并发";
    }


  

}
