package com.mashibing.javabackend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    public void addString(String key,String value){
        ValueOperations valueOperations=stringRedisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    public String getString(String key){
        ValueOperations valueOperations=stringRedisTemplate.opsForValue();
        String value = (String)valueOperations.get(key);
        return value;
    }

    public boolean deleteString(String key){
        Jedis jedis=new Jedis();
        if(jedis.exists(key)){
            jedis.del(key);
            return true;
        }
        return false;
    }

    public void setExpireTime(String key,String value,int time,String timeType){
        switch (timeType){
            case "days":
                stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.DAYS);
                break;
            case "hours":
                stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.HOURS);
                break;
            case "microseconds":
                stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.MICROSECONDS);
                break;
            case "minutes":
                stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
                break;
            case "milliseconds":
                stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
                break;
            case "seconds":
                stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
                break;
            case "nanoseconds":
                stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.NANOSECONDS);
                break;
        }
    }


}
