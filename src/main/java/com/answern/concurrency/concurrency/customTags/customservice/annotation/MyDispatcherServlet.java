package com.answern.concurrency.concurrency.customTags.customservice.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * 需求名称: 本来是写了一个spring mvc 发现boot里面不好实现<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/26 20:42]  <br/>
 * 版本:[v1.0]   <br/>
 */

public class MyDispatcherServlet extends   HttpServlet {

    private final String packagePath ="com.answern.concurrency.concurrency.customTags.customservice";

    /**配置文件中的内容**/
    private Properties properties = new Properties();
    /** 所有类 **/
    private List<String> classNames = new ArrayList<String>();

    /**  所有类的实例，key是注解的value,value是所有类的实例 **/
    private Map<String, Object> instanceMap = new HashMap<String, Object>();
    /**自定义的注解map**/
    Map<String, Method> handlerMapping = new HashMap<String, Method>();

    private Map<String, Object> controllerMap  =new HashMap<>();

    /**要解析的类**/


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(handlerMapping.isEmpty()){
            return;
        }

        String url =req.getRequestURI();
        String contextPath = req.getContextPath();

        url=url.replace(contextPath, "").replaceAll("/+", "/");

        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 NOT FOUND!");
            return;
        }

        Method method =this.handlerMapping.get(url);

        //获取方法的参数列表
        Class<?>[] parameterTypes = method.getParameterTypes();

        //获取请求的参数
        Map<String, String[]> parameterMap = req.getParameterMap();

        //保存参数值
        Object [] paramValues= new Object[parameterTypes.length];

        //方法的参数列表
        for (int i = 0; i<parameterTypes.length; i++){
            //根据参数名称，做某些处理
            String requestParam = parameterTypes[i].getSimpleName();


            if (requestParam.equals("HttpServletRequest")){
                //参数类型已明确，这边强转类型
                paramValues[i]=req;
                continue;
            }
            if (requestParam.equals("HttpServletResponse")){
                paramValues[i]=resp;
                continue;
            }
            if(requestParam.equals("String")){
                for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
                    String value =Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                    paramValues[i]=value;
                }
            }
        }
        //利用反射机制来调用
        try {
            method.invoke(this.controllerMap.get(url), paramValues);//第一个参数是method所对应的实例 在ioc容器中
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {



        //1  加载配置文件 并解析
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //2 扫描关联类   扫描到指定包下的所有类
        doScanner(properties.getProperty("scanPackage"));
        //3.拿到扫描到的类,通过反射机制,实例化,并且放到ioc容器中(k-v  beanName-bean) beanName默认是首字母小写
        //3 初始化 ，获取所有的自定义的标签
        doInstance();
        //4 建立一个映射关系  将自定义标签的所有属性获取到，并放入map
        dohanderMap();
        //5 反转控制，根据注解，把service中的注入到controller中的service
       // doIoc();

        //6 注入完之后开始映射url和method
       // buildUrlMethodMapping(instanceNameMap, urlMethodMapping);


    }

    private void doIoc() {
        if (instanceMap.isEmpty()){return;}
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            // 拿到里面的所有属性
            Field fields[] = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                // 可访问私有属性
                field.setAccessible(true);
                if (field.isAnnotationPresent(MyAutoWrite.class)) {

                    MyAutoWrite quatifier = field.getAnnotation(MyAutoWrite.class);
                    String value = quatifier.name();
                    field.setAccessible(true);

                    // 没有配别名注入的时候
                    if (value.isEmpty()) {
                        // 直接获取
                        try {
                            // 根据类型来获取他的实现类
                            Object object = instanceMap.get(field.getType());
                            field.set(entry.getValue(), object);
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            // 将被注入的对象
                            field.set(entry.getValue(), instanceMap.get(value));
                        } catch (Exception e) {
                            throw new RuntimeException("开始注入时出现了异常");
                        }
                    }



                }
            }
        }

    }

    private void dohanderMap() {
        if(instanceMap.isEmpty()){ return; }
        try {
            for ( Map.Entry<String, Object> entry :instanceMap.entrySet()){
                Class<? extends Object> clazz = entry.getValue().getClass();
                if (clazz.isAnnotationPresent(MyController.class)) {

                    String baseUrl ="";
                    if(clazz.isAnnotationPresent(MyRequestMapping.class)){
                        MyRequestMapping annotation = clazz.getAnnotation(MyRequestMapping.class);
                        baseUrl=annotation.name();
                    }
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        if(!method.isAnnotationPresent(MyRequestMapping.class)){
                            continue;
                        }
                        MyRequestMapping annotation = method.getAnnotation(MyRequestMapping.class);
                        String url = annotation.name();

                        url =(baseUrl+"/"+url).replaceAll("/+", "/");
                        handlerMapping.put(url,method);
                        controllerMap.put(url,clazz.newInstance());
                        System.out.println(url+","+method);
                    }

                } else {
                    continue;
                }

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doInstance() {
        if(classNames.isEmpty()){
            return;
        }
        try {
            for(String className:classNames){
                //把类搞出来,反射来实例化(只有加@MyController需要实例化)
                Class<?> clazz  = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {

                    Object instance = clazz.newInstance();
                    MyController controller =  clazz.getAnnotation(MyController.class);
                    // 如果没有设置别名，那么用类名首字母小写做key
                    if (controller.name().isEmpty()) {
                        instanceMap.put(firstLowerName(clazz.getSimpleName()), instance);
                    }else{
                        // 如果设置了别名那么用别名做key
                        String key = controller.name();
                        instanceMap.put(key, instance);
                    }
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    Object instance = clazz.newInstance();
                    MyService service =  clazz.getAnnotation(MyService.class);

                    // 如果没有设置别名，那么用类名首字母小写做key
                    if (service.name().isEmpty()) {
                        instanceMap.put(firstLowerName(clazz.getSimpleName()), instance);
                    }else{
                        // 如果设置了别名那么用别名做key
                        String key = service.name();
                        instanceMap.put(key, instance);
                    }
                } else {
                    continue;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doScanner(String scanPackage) {
        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<>();

        // 获取包的名字   把所有的.替换成/
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        for (File file :classDir.listFiles()){
            if(file.isDirectory()){
                //递归读取包
                doScanner(scanPackage+"."+file.getName());
            }else {
                String className =scanPackage +"." +file.getName().replace(".class", "");
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String location){
        //把web.xml中的contextConfigLocation对应value值的文件加载到流里面
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            //用Properties文件加载文件里的内容
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关流
            if(null!=resourceAsStream){
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 首字母小写
     * @param name
     * @return
     */
    private String firstLowerName(String name) {
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return  String.valueOf(chars);
    }

}
