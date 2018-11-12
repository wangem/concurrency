package com.answern.concurrency.concurrency.customTags.prox;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/10/23 16:36]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProxClientFactoryBean implements FactoryBean<Object>, InitializingBean, ApplicationContextAware {

    private String name;
    private String value  ;
    private boolean isLogs ;

    private ApplicationContext applicationContext;

    private Class<?> type;

    @Nullable
    @Override
    public Object getObject() throws Exception {
        System.out.println("this is FactoryBeanProxy  getObject");

        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //Assert.hasText(this.name, "Name must be set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return this.type;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }

//    @Override
//    public Object getObject() throws Exception {
//        return targeter.target(this, builder, context, new HardCodedTarget<>(
//                this.type, this.name, url));
//    }

}
