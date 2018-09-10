package com.answern.concurrency.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/8 17:29]  <br/>
 * 版本:[v1.0]   <br/>
 */
public class CountdownLatchTest1 {

    public static void main(String[] args) {
        try {
            CountdownLatchTest1 service = new CountdownLatchTest1();
            CountDownLatch begin = new CountDownLatch(1);//裁判员鸣枪信号
            CountDownLatch end = new CountDownLatch(10);//10名参赛选手结束信号
            MyThread[] threadArray = new MyThread[10];
            for(int i = 0 ; i < 10; i++){
                threadArray[i] = new MyThread(service,begin,end);
                threadArray[i].setName((i + 1) + " 号选手 ");
                threadArray[i].start();
            }
            System.out.println("在等待裁判员鸣枪  " + System.currentTimeMillis());
            long t1 = System.currentTimeMillis();//记录比赛的开始时间
            begin.countDown();//裁判员鸣枪了
            end.await();//等待10个参赛选手都跑完100米
            long t2 = System.currentTimeMillis();//记录比赛的结束时间
            System.out.println("所有参赛选手都完成了100米赛跑，赛跑总耗时是  " + (t2-t1));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    public void testMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin timer " + System.currentTimeMillis());
            Thread.sleep((long) (Math.random()*10));//模拟每个跑步选手跑完100米所需的时间
            System.out.println(Thread.currentThread().getName() + " end timer " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
class MyThread extends Thread {
    private CountdownLatchTest1 service;
    private final CountDownLatch begin;
    private final CountDownLatch end;


    public MyThread(CountdownLatchTest1 service, CountDownLatch begin, CountDownLatch end){
        this.service = service;
        this.begin  = begin;
        this.end  = end;
    }

    public void run(){
        try {
            begin.await();//每个参赛选手都站在自己的跑道上，做好了比赛的准备，正在等裁判鸣枪开始比赛
            service.testMethod();//听到鸣枪后比赛开始了
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            end.countDown();//其中的一个参赛选手已经跑完了
        }

    }

}
