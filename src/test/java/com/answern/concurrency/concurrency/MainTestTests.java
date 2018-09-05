package com.answern.concurrency.concurrency;


import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;


import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTestTests extends  BaseTest{

	@Test
	public void executeAsyncTask() {
        Map<String, Object> map = new HashMap<>();

        MvcResult result = null;// 返回执行请求的结果
        try {
            result = mockMvc.perform(post("/maintest/executeAsyncTask").content(JSONObject.toJSONString(map)))
                    .andExpect(status().isOk())// 模拟向testRest发送get请求
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
