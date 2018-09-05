package com.answern.concurrency.concurrency.base;


import org.springframework.scheduling.annotation.Async;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/4 11:59]  <br/>
 * 版本:[v1.0]   <br/>
 */
public class TestRunMain {



        public static void main(String args[]) {
            RunnableImp R1 = new RunnableImp("Thread-1","url");
            R1.start();

            RunnableImp R2 = new RunnableImp("Thread-2","url");
            R2.start();


        }



}
