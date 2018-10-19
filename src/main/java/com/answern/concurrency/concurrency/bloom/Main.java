package com.answern.concurrency.concurrency.bloom;

import java.util.ArrayList;
import java.util.List;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/10/16 8:59]  <br/>
 * 版本:[v1.0]   <br/>
 */
public class Main {
    public static void main(String[] args) {
        BloomFilter bf = new BloomFilter();
        List<String> strs = new ArrayList<>();
        strs.add("123456");
        strs.add("hello word");
        strs.add("transDocId");
        strs.add("123456");
        strs.add("transDocId");
        strs.add("hello word");
        strs.add("test");
        for (int i=0;i<strs.size();i++) {
            String s = strs.get(i);
            boolean bl = bf.contains(s);
            if(bl){
                System.out.println(i+","+s+"==true");
            }else{
                bf.add(s);
            }
//            boolean b2 = bf.contains(s);
//            if(b2){
//                System.out.println(i+","+s+"=====true");
//            }else{
//                System.out.println(i+","+s+"=====false");
//            }
        }
    }

}
