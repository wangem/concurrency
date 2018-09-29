package com.answern.concurrency.concurrency.readApplication;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/29 10:25]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Configuration
@Data
@Component
@ConfigurationProperties(prefix = "com.answern")
public class ReadConfig {

    private String foo;
    private String name;
    private String id;

}
