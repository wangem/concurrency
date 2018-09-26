package com.answern.concurrency.concurrency.customTags.prox;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * 需求名称:定义将扫描包下的类<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/26 17:30]  <br/>
 * 版本:[v1.0]   <br/>
 */
public class DoScanner extends ClassPathBeanDefinitionScanner {

    public DoScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 将自定义的注解放入监听器中
     */
    @Override
    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(ProxDosomething.class));
    }
    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions =   super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
            definition.setBeanClass(FactoryBeanProxy.class);
        }       return beanDefinitions;
    }
    @Override
    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata().hasAnnotation(ProxDosomething.class.getName());
    }


}
