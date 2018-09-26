package com.answern.concurrency.concurrency.customTags.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/18 8:13]  <br/>
 * 版本:[v1.0]   <br/>
 */

@Aspect
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class AopAspect {

    private Logger logger = LoggerFactory.getLogger(AopAspect.class);

    @Before("@annotation(test)")// 拦截被TestAnnotation注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)，具体百度一下资料一大堆
    public void beforeTest(JoinPoint joinPoint, AopDosomething test) throws Throwable {

        logger.info("name:{},getName{}",test.name(),joinPoint.getArgs()[0]);
    }

    @After("@annotation(test)")
    public void after(JoinPoint point, AopDosomething test){
        System.out.println("after......"+ test.name());
    }

    /**
     * 返回参数中类似  "#user.getUserName()" 的数据
     *
     * 调用方法  Method method = getMethod(joinPoint);　//自定义注解类
     *           Cacheable cacheable = method.getAnnotation(Cacheable.class);<br>　　　　　//获取key值　<br>　　　　　String key = cacheable.key();<br>　　　　　String fieldKey=cacheable.fieldKey();<br>　　　　　　 　
     *           获取方法的返回类型,让缓存可以返回正确的类型
     *           Class returnType=((MethodSignature)joinPoint.getSignature()).getReturnType();<br>　　　　<br>　　　　　下面就是根据业务来自行操作　
     * @param pjp
     * @return
     */
    private Method getMethod(JoinPoint pjp) {
        //获取参数的类型
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            logger.error("annotation no sucheMehtod", e);
        } catch (SecurityException e) {
            logger.error("annotation SecurityException", e);
        }
        return method;

    }

}
