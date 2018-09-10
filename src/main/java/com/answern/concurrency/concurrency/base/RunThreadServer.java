package com.answern.concurrency.concurrency.base;

import sun.applet.Main;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 需求名称:并发线程基础类<br/>
 *  当线程要并发执行某一个方法时 继承此类 实现verifyService 方法即可
 * @author [wem] <br/>
 * 创建时间:[2018/9/6 21:19]  <br/>
 * 版本:[v1.0]   <br/>
 */
public abstract  class RunThreadServer implements Runnable {

    private CountDownLatch begin;
    private CountDownLatch end;

    private Map producesMap;


    public RunThreadServer( CountDownLatch  begin, CountDownLatch end, Map producesMap){
        super();
        this.begin = begin;
        this.end = end;
        this.producesMap = producesMap;
    }
    @Override
    public void run() {
        try {
            begin.await();
            verifyService(producesMap);
        } catch (Throwable  e) {
            e.printStackTrace(System.err);
        } finally {
            System.out.println("end="+end.getCount());
            end.countDown();//其中的一个参赛选手已经跑完了
        }
    }

    /**
     * 并发线程要执行的方法
     */
    public abstract void verifyService(Map producesMap);




}
