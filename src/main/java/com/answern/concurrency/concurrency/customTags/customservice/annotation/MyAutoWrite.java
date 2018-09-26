package com.answern.concurrency.concurrency.customTags.customservice.annotation;

import java.lang.annotation.*;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/26 21:01]  <br/>
 * 版本:[v1.0]   <br/>
 */

@Target({  ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutoWrite {
    String name() default "";
}
