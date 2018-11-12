package com.answern.concurrency.concurrency;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateControllerTest extends  BaseTest{

    @Autowired
    RedisTemplate redisTemplate;



	@Test
	public void executeAsyncTask() {
//        String expectedResult = "并发";
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/resttemplate/main?i=3").accept(MediaType.APPLICATION_JSON)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            String content = mvcResult.getResponse().getContentAsString();
            System.out.println(content);
            Assert.assertTrue("错误，正确的返回值为200", status == 200);
            Assert.assertFalse("错误，正确的返回值为200", status != 200);
//            Assert.assertTrue("数据一致", expectedResult.equals(content));
//            Assert.assertFalse("数据不一致", !expectedResult.equals(content));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void redisTest(){
//        try {
//           // Connection connection = dataSource.getConnection();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        redisTemplate.delete("0000");
    }



}
