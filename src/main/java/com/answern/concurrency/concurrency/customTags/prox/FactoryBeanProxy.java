//package com.answern.concurrency.concurrency.customTags.prox;
//
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.cglib.core.SpringNamingPolicy;
//import org.springframework.cglib.proxy.Enhancer;
//import org.springframework.cglib.proxy.MethodInterceptor;
//import org.springframework.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
///**
// * 需求名称:
// * 类描述:[一句话描述该类的功能]<br/>
// *
// * @author [wem] <br/>
// * 创建时间:[2018/9/26 17:44]  <br/>
// * 版本:[v1.0]   <br/>
// */
// public class FactoryBeanProxy<T> implements InitializingBean, FactoryBean<T> {
//        private String innerClassName;
//        public void setInnerClassName(String innerClassName) {
//            this.innerClassName = innerClassName;
//        }
//        @Override
//        public T getObject() throws Exception {
//            Class innerClass = Class.forName(innerClassName);
//            System.out.println("this is FactoryBeanProxy  getObject");
//            if (innerClass.isInterface()) {
//                return (T) InterfaceProxy.newInstance(innerClass);
//            } else {
//                Enhancer enhancer = new Enhancer();
//                enhancer.setSuperclass(innerClass);
//                enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
//                enhancer.setCallback(new MethodInterceptorImpl());
//                return (T) enhancer.create();
//            }
//        }
//        @Override
//        public Class<?> getObjectType() {
//            try {
//                return Class.forName(innerClassName);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//        @Override
//        public boolean isSingleton() {
//            return true;
//        }
//        @Override
//        public void afterPropertiesSet() throws Exception {
//        }
//    }
//       class InterfaceProxy implements InvocationHandler {
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("ObjectProxy execute:" + method.getName());
//            return method.invoke(proxy, args);
//        }
//        public static <T> T newInstance(Class<T> innerInterface) {
//            ClassLoader classLoader = innerInterface.getClassLoader();
//            Class[] interfaces = new Class[] { innerInterface };
//            InterfaceProxy proxy = new InterfaceProxy();
//            return (T) Proxy.newProxyInstance(classLoader, interfaces,  proxy);
//        }
//    }
//       class MethodInterceptorImpl implements MethodInterceptor {
//        @Override
//        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//            System.out.println("MethodInterceptorImpl:" + method.getName());
//            return methodProxy.invokeSuper(o, objects);
//        }
//    }
