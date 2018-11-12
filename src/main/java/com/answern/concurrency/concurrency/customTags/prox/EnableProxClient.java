package com.answern.concurrency.concurrency.customTags.prox;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/10/23 8:56]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(LoggerClientsRegistrar.class)
public @interface EnableProxClient {

    String[] basePackages() default {};

    String[] value() default {};

    Class<?>[] basePackageClasses() default {};
    /**
     * List of classes annotated with @FeignClient. If not empty, disables classpath scanning.
     * @return
     */
    Class<?>[] clients() default {};
}
