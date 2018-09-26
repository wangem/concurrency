package com.answern.concurrency.concurrency.customTags.customservice;

import com.answern.concurrency.concurrency.customTags.customservice.annotation.MyController;
import com.answern.concurrency.concurrency.customTags.customservice.annotation.MyService;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/9/26 21:16]  <br/>
 * 版本:[v1.0]   <br/>
 */
@MyService
public class AnService {

    public String thisInit(){
        return "this is my Service";
    }
}
