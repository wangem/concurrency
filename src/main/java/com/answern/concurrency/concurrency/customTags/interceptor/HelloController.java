package com.answern.concurrency.concurrency.customTags.interceptor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/27 18:25]  <br/>
 * 版本:[v1.0]   <br/>
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    // 配置注解权限, 允许身份为admin, 或者说允许权限为admin的人访问
    @Access(authorities = {"admin"})
    public String hello() {
        System.out.println("Hello, admin");
        return "Hello, admin";
    }
}