package com.answern.concurrency.concurrency.countdownLatch;

import com.answern.concurrency.concurrency.countdownLatch.RunThreadServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/8 15:49]  <br/>
 * 版本:[v1.0]   <br/>
 */
@ComponentScan
public class RunThreadServerImp  extends RunThreadServer {


    private RestTemplate restTemplate ;

    private Map producesMap;

    public RunThreadServerImp(CountDownLatch begin,CountDownLatch end, Map producesMap) {
        super(begin,end,producesMap);
        this.producesMap = producesMap;

    }

    @Override
    public void verifyService(Map producesMap) {
        String url = producesMap.get("url").toString();
        String i = producesMap.get("i").toString();
        this.restTemplate = (RestTemplate)producesMap.get("restTemplate");
        try
        {
            System.out.println( " is UP");
            //Thread.sleep(7000);
            restTemplate.getForObject(url+"?i="+i, String.class);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       // System.out.println(this.getServiceName() + " is UP");
    }
}
