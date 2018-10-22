package com.answern.concurrency.concurrency;

import com.answern.concurrency.concurrency.distributed.DisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/10/22 16:33]  <br/>
 * 版本:[v1.0]   <br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DisServiceTest {
    @Autowired
    DisService disService;

    @Test
    public void disTransactionalMethodTest(){
        disService.TransactionalMethod();
    }
}
