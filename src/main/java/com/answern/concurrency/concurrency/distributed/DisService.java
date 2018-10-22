package com.answern.concurrency.concurrency.distributed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

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
    @Autowired
    JdbcTemplate jdbcTemplate;

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

    @Transactional
    public String TransactionalMethod(){
        SingleColumnRowMapper<User> userSingleColumnRowMapper = new SingleColumnRowMapper<>(User.class);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(" select id ,name,sex from user ");
        for(Map<String, Object>  o:maps){
            System.out.println(o.get("id").toString()+o.get("name")+o.get("sex"));

        }
        System.out.println("============================================");
        jdbcTemplate.update(" UPDATE user  set  name='yy' where  id=1 ");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==============over=======================");
        List<Map<String, Object>> maps1 = jdbcTemplate.queryForList(" select id ,name,sex from user ");
        for(Map<String, Object>  o:maps1){
            System.out.println(o.get("id").toString()+o.get("name")+o.get("sex"));

        }
        return "ok";
    }
}
