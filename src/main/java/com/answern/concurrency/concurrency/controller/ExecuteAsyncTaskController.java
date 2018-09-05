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

/**
 * 需求名称:
 * 类描述:[调用异步方法测试]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/3 16:04]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Api(value = "调用异步方法测试", description = "调用异步方法测试")
@RestController
@RequestMapping("maintest")
public class ExecuteAsyncTaskController {

    private final static Logger logger = LoggerFactory.getLogger(ExecuteAsyncTaskController.class);

    @Autowired
    ExecuteAsyncTaskService executeAsyncTaskService;
    /**
     * 调用异步方法
     * @return
     */
    @ApiOperation(value = "调用异步方法", notes = "调用异步方法")
    @RequestMapping(value = "executeAsyncTask" ,method = RequestMethod.GET)
    public String executeAsyncTask(){
        executeAsyncTaskService.executeAsyncTask(50);
        System.out.println("异步任务执行okkokok");
        return "调用异步方法ok";
    }

  

}
