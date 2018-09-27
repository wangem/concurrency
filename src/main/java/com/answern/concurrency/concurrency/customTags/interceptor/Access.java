package com.answern.concurrency.concurrency.customTags.interceptor;

import java.lang.annotation.*;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/27 18:22]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {

    String[] value() default {};

    String[] authorities() default {};

    String[] roles() default {};

}
