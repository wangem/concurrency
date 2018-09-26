package com.answern.concurrency.concurrency.customTags.customservice.annotation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/26 20:42]  <br/>
 * 版本:[v1.0]   <br/>
 */
public class MyDispatcherServlet extends HttpServlet {

    private final String packagePath ="com.answern.concurrency.concurrency.customTags.customservice";
    /** 所有类 **/
    private List<String> classNames = new ArrayList<String>();

    /**  所有类的实例，key是注解的value,value是所有类的实例 **/
    private Map<String, Object> instanceMap = new HashMap<String, Object>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        //1  加载配置文件 并解析
        doLoadConfig();
        //2 扫描关联类
        doScanner(packagePath);
        //3 初始化  过滤和实例化
        doInstance();
        //4 建立一个映射关系
        dohanderMap();
        //5 反转控制，根据注解，把service中的注入到controller中的service
        doIoc();


    }

    private void doIoc() {
    }

    private void dohanderMap() {
    }

    private void doInstance() {
        if(classNames.isEmpty()){
            return;
        }
        try {
            for(String className:classNames){
                Class<?> cName = Class.forName(className.replace(".class", "").trim());
                if (cName.isAnnotationPresent(MyController.class)) {
                    Object instance = cName.newInstance();
                    MyController controller = (MyController) cName.getAnnotation(MyController.class);
                    String key = controller.name();
                    instanceMap.put(key, instance);
                } else if (cName.isAnnotationPresent(MyService.class)) {
                    Object instance = cName.newInstance();
                    MyService service = (MyService) cName.getAnnotation(MyService.class);
                    String key = service.name();
                    instanceMap.put(key, instance);
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

        // 获取包的名字 并进行替换
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll(".","\\"));
        File classDir = new File(url.getFile());
        for (File file :classDir.listFiles()){
            if(file.isDirectory()){
                doScanner(file.getPath());
            }else {
                classNames.add(file.getPath() + "." + file.getName());
            }
        }
    }

    private void doLoadConfig(){

    }
}
