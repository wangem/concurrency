package com.answern.concurrency.concurrency;

/**
 * 需求名称:  这是一个测试类的基本例子类
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/4 15:35]  <br/>
 * 版本:[v1.0]   <br/>
 */
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootStudentDataJpaApplicationTests {


    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationConnect;


    String expectedJson;

    @Before
    public void setUp() throws JsonProcessingException {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();

    }

    @Test
    public void testShow() throws Exception {
        String expectedResult = "hello world!";
        String uri = "/show";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        Assert.assertTrue("错误，正确的返回值为200", status == 200);
        Assert.assertFalse("错误，正确的返回值为200", status != 200);
        Assert.assertTrue("数据一致", expectedResult.equals(content));
        Assert.assertFalse("数据不一致", !expectedResult.equals(content));
    }

    protected String Obj2Json(Object obj) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    @Test
    public void testShowDaoInt() throws Exception {
//        List<TestPOJO> testPOJOList = testServices.showDao(10);
//        expectedJson = Obj2Json(testPOJOList);

        String uri="/showDao?age=10";
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status=mvcResult.getResponse().getStatus();
        String content=mvcResult.getResponse().getContentAsString();

        Assert.assertTrue("错误，正确的返回值为200", status == 200);
        Assert.assertFalse("错误，正确的返回值为200", status != 200);
        Assert.assertTrue("数据一致", expectedJson.equals(content));
        Assert.assertFalse("数据不一致", !expectedJson.equals(content));
    }

    @Test
    public void testShowDaoString() throws Exception {
//        List<HotelDto> hotelDtoList=testServices.findByCountry("US");
//        expectedJson = Obj2Json(hotelDtoList);

        String uri="/country/US";
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status=mvcResult.getResponse().getStatus();
        String content=mvcResult.getResponse().getContentAsString();

        Assert.assertTrue("错误，正确的返回值为200", status == 200);
        Assert.assertFalse("错误，正确的返回值为200", status != 200);
        Assert.assertTrue("数据一致", expectedJson.equals(content));
        Assert.assertFalse("数据不一致", !expectedJson.equals(content));
    }
}
