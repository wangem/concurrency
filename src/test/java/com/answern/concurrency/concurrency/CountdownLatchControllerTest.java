package com.answern.concurrency.concurrency;


import com.answern.concurrency.concurrency.service.CountdownLatchRestTemplateService;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CountdownLatchControllerTest extends  BaseTest{

	@Test
	public void executeAsyncTask() {
        String expectedResult = "并发";
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/countdownLatch/index").accept(MediaType.APPLICATION_JSON)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            String content = mvcResult.getResponse().getContentAsString();
            System.out.println(content);
            Assert.assertTrue("错误，正确的返回值为200", status == 200);
            Assert.assertFalse("错误，正确的返回值为200", status != 200);
            Assert.assertTrue("数据一致", expectedResult.equals(content));
            Assert.assertFalse("数据不一致", !expectedResult.equals(content));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
