package com.answern.concurrency.concurrency;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateControllerTest extends  BaseTest{

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



}
