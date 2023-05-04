package com;

import com.mashibing.javabackend.utils.RedisUtil;

import javax.annotation.Resource;

public class TestClass {
    @Resource
    private RedisUtil redisUtil;

    public void test(){
        redisUtil.addString("key1","123");
    }
}
