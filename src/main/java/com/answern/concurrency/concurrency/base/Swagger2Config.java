package com.answern.concurrency.concurrency.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/4 16:15]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * UI页面显示信息
     */
    private final String SWAGGER2_API_BASEPACKAGE = "com.answern.concurrency";
    private final String SWAGGER2_API_TITLE = "ms-API";
    private final String SWAGGER2_API_DESCRIPTION = "com.answern.concurrency";
    private final String SWAGGER2_API_CONTACT = "0000";
    private final String SWAGGER2_API_VERSION = "1.0";

    /**
     * createRestApi
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER2_API_BASEPACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * apiInfo
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SWAGGER2_API_TITLE)
                .description(SWAGGER2_API_DESCRIPTION)
                .contact(SWAGGER2_API_CONTACT)
                .version(SWAGGER2_API_VERSION)
                .build();

    }
}
