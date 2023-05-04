package com;

import com.mashibing.javabackend.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestDemo {
//    @Resource
//    private RedisUtil redisUtil;

    public static void main(String[] args) {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
//        valueOperations.set("key1","123");

        TestClass tClass = new TestClass();
        tClass.test();
        System.out.println("TestDemo");
    }

    //@org.junit.Test
//    @Test
//    public void redisTest(){
//        //redisUtil.addString("key1","123");
//        System.out.println("redisTest");
//    }

}
