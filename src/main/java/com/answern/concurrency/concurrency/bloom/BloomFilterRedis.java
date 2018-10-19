package com.answern.concurrency.concurrency.bloom;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * 需求名称:
 * 类描述:[一句话描述该类的功能]<br/>
 *
 * @author [wem] <br/>
 * 创建时间:[2018/10/19 10:39]  <br/>
 * 版本:[v1.0]   <br/>
 */
@Component
public class BloomFilterRedis {

    @Autowired
    RedisTemplate redisTemplate;

    /**预计插入量**/
    private long expectedInsertions = 1000;
    /**可接受的错误率**/
    private double fpp = 0.001F;


    /**布隆过滤器的键在Redis中的前缀 利用它可以统计过滤器对Redis的使用情况**/
    private String redisKeyPrefix = "bf:";

    /**bit数组长度 **/
    private long numBits = optimalNumOfBits(expectedInsertions, fpp);
    /** hash函数数量**/
    private int numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);

    /** 计算hash函数个数 方法来自guava **/
    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }
    /** 计算bit数组长度 方法来自guava **/
    private long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * 判断keys是否存在于集合where中
     * 根据getIndexs(key)算出的下标去取值
     */
    public boolean isExist(String where, String key) {
        long[] indexs = getIndexs(key);
        boolean result = false;
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        RedisOperations<String, String> operations = ops.getOperations();

        System.out.println("indexss =="+indexs);
        for (long index : indexs) {
            System.out.println("index =="+index);
            result =ops.getBit(getRedisKey(where), index);
            if (!result) {
                put(where, key);
                return result;
            }
        }
        return result;
    }

    /**
     * 将key存入redis bitmap
     * redis中存入的是：将where集合中加入下标
     */
    private void put(String where, String key) {
        long[] indexs = getIndexs(key);
        ValueOperations<String,String> pipeline = redisTemplate.opsForValue();
        for (long index : indexs) {
            pipeline.setBit(getRedisKey(where), index, true);
        }
    }

    /**
     * 根据key获取bitmap下标 方法来自guava
     */
    private long[] getIndexs(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        long[] result = new long[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result[i] = combinedHash % numBits;
        }
        return result;
    }

    /**
     * 获取一个hash值 方法来自guava
     */
    private long hash(String key) {
        Charset charset = Charset.forName("UTF-8");
        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
    }

    private String getRedisKey(String where) {
        return redisKeyPrefix + where;
    }


    public void setExpectedInsertions(long expectedInsertions) {
        this.expectedInsertions = expectedInsertions;
    }

    public void setFpp(double fpp) {
        this.fpp = fpp;
    }

    public void setRedisKeyPrefix(String redisKeyPrefix) {
        this.redisKeyPrefix = redisKeyPrefix;
    }

}
