package com.answern.concurrency.concurrency.customTags.prox;

import java.lang.annotation.*;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/26 16:57]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxClient {

    String name()  default "";

    String value() default "";

    boolean isLogs() default false;
}
