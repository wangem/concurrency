package com.answern.concurrency.concurrency.Dao;

import com.answern.concurrency.concurrency.base.RunThreadServer;

import java.util.concurrent.CountDownLatch;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/8 15:49]  <br/>
 * 版本:[v1.0]   <br/>
 */
public class RunThreadServerImp  extends RunThreadServer {


    /**
     *
     * @param serviceName  指令名称
     * @param begin
     */
    public RunThreadServerImp(String serviceName, CountDownLatch begin,CountDownLatch end) {
        super(serviceName, begin,end);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(7000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
