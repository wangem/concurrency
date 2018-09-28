package com.answern.concurrency.concurrency.distributed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 需求名称:分布式编程式事物<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/27 20:51]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Service
public class DisService {

    @Autowired
    private TransactionTemplate transactionTemplate;

    public String index(){
        System.out.println("这里写其他事物");
        transactionTemplate.execute(new TransactionCallback<String>(){
            @Nullable
            @Override
            public String  doInTransaction(TransactionStatus status) {
                //数据库操作1
                //数据库操作2
                return null;
            }
        });
        return "ok";
    }
}
