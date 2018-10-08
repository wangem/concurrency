package com.answern.concurrency.concurrency.designPattern.observer;

import java.util.Vector;

/**
 *  被观察者 <br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/10/8 16:42]  <br/>
 * 版本:[v1.0]   <br/>
 */
public abstract class Subject {

    private Vector<Observer> obj = new Vector();

    public void addObserver(Observer obs){
        this.obj.add(obs);
    }
    public void delObserver(Observer obs){
        this.obj.remove(obs);
    }

    protected  String notifyObserver(){
        for (Observer obs : obj){
            obs.update();
        }
        return "观察完毕";
    }
    public abstract void doSomeThing();
}
