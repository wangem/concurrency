package com.answern.concurrency.concurrency.designPattern.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/10/8 16:56]  <br/>
 * 版本:[v1.0]   <br/>
 */
public class ObserverImp extends Observer {
    private Logger logger = LoggerFactory.getLogger(SubjectImp.class);
    @Override
    public void update() {
        logger.info("观察者1收到信息，并进行处理。");
    }
}
