package com.answern.concurrency.concurrency.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/3 16:04]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Api(value = "测试主类接口", description = "测试主类接口")
@RestController
@RequestMapping("resttemplate")
public class RestTemplateController {

    private final static Logger logger = LoggerFactory.getLogger(RestTemplateController.class);



    @Autowired
    RestTemplate restTemplate ;
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String test(String i){
        logger.info( "this is amin=="+i);
        return " this is main";
    }

    /**
     * 直接使用方式
     * @return
     */
    @ApiOperation(value = "测试restTemplate直接使用方式", notes = "restTemplate直接使用方式")
    @RequestMapping(value = "test1",method = RequestMethod.GET)
    public String restTemplate(){

        String quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", String.class);
        String quoteString = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", String.class);
        System.out.println(quoteString);
        return quoteString;
    }

    /**
     * 使用RestTemplate发送get请求
     * @return
     */
    @ApiOperation(value = "使用RestTemplate发送get请求", notes = "使用RestTemplate发送get请求")
    @RequestMapping(value = "get" ,method = RequestMethod.GET)
    public String getRestTemplate(){
        //get json数据
        String json = restTemplate.getForEntity("http://baidu.com", String.class).getBody();
        return json;
    }
    /**
     * 使用RestTemplate发送post请求
     * @return
     */
    @ApiOperation(value = "使用RestTemplate发送post请求", notes = "使用RestTemplate发送post请求")
    @RequestMapping(value = "post" , method = RequestMethod.GET)
    public String postRestTemplate(){
        //post json数据
        JSONPObject postData = new JSONPObject("data", "request for post");

        JSONPObject json = restTemplate.postForEntity("http://www.baidu.com", postData, JSONPObject.class).getBody();
        return "json";
    }
    /**
     * 使用RestTemplate发送请求  设置请求头
     * @return
     */
    @ApiOperation(value = "使用RestTemplate发送请求  设置请求头", notes = "使用RestTemplate发送请求  设置请求头")
    @RequestMapping(value = "title",method = RequestMethod.GET)
    public String getTitleRestTemplate(){

        //post json string data
//return string
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONPObject jsonObj = new JSONPObject("data", "request for post");
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        String result = restTemplate.postForObject("http://www.baidu.com", formEntity, String.class);
      return result;
    }





}
