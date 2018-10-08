package com.answern.concurrency.concurrency.designPattern.observer;

/**
 * <br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/10/8 17:55]  <br/>
 * 版本:[v1.0]   <br/>
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new SubjectImp();
        subject.addObserver(new ObserverImp());
        subject.doSomeThing();
    }
}
