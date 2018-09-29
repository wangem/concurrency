package com.answern.concurrency.concurrency.readApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/29 10:03]  <br/>
 * 版本:[v1.0]   <br/>
 */
@RestController
@RequestMapping("/application")
public class ReadController {

    @Autowired
    private ReadConfig readConfig;
    @Autowired
    private ReadConfigList readConfigList;
    @Autowired
    private ReadConfigList1 readConfigList1;

    @RequestMapping("index")
    public String redA(){
        System.out.println(readConfig.getFoo());
        System.out.println(readConfig.getName());
        System.out.println(readConfig.getId());
        return "ok";
    }

    /**
     * 配置读取方法2
     */
    @Value("${com.answern.name}")
    private String name ;
    @RequestMapping("index1")
    public String redA1(){
        System.out.println(this.name);
        return "ok";
    }

    @RequestMapping("indexList")
    public String indexList(){
        List<String> ids = readConfigList.getId();
        for (int i =0;i<ids.size();i++){
            System.out.println("readConfigList.id"+i+"=="+ids.get(i));
        }

        List<AnswernList> ids1 = readConfigList1.getAnswernList();
        for (int i =0;i<ids1.size();i++){
            System.out.println("readConfigList.id"+i+"=="+ids1.get(i).getId());
            System.out.println("readConfigList.foo"+i+"=="+ids1.get(i).getFoo());
            System.out.println("readConfigList.name"+i+"=="+ids1.get(i).getName());
        }

        return "ok";
    }
}
