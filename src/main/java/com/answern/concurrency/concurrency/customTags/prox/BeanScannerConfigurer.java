//package com.answern.concurrency.concurrency.customTags.prox;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//
///**
// * 需求名称: 定义 spring 启动时要扫描的包<br/>
// *
// * Spring提供了一些的接口使程序可以嵌入Spring的加载过程。这个类中的继承ApplicationContextAware接口，Spring会读取ApplicationContextAware类型的的JavaBean，并调用setApplicationContext(ApplicationContext applicationContext)传入Spring的applicationContext。同样继承BeanFactoryPostProcessor接口，Spring会在BeanFactory的相关处理完成后调用postProcessBeanFactory方法，进行定制的功能。
// * @author [wem] <br/>
// * 创建时间:[2018/9/26 17:23]  <br/>
// * 版本:[v1.0]   <br/>
// */
//@Component
//public class BeanScannerConfigurer implements BeanFactoryPostProcessor, ApplicationContextAware {
//    private ApplicationContext applicationContext;
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        DoScanner scanner = new DoScanner((BeanDefinitionRegistry) beanFactory);
//        scanner.setResourceLoader(this.applicationContext);
//        scanner.scan("com.answern.concurrency.concurrency.customTags.prox");
//    }
//
//
//
//}
