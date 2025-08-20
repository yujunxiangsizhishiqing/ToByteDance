package test;

import com.mashibing.javabackend.utils.RedisUtil;
import demoClass.RedisController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Component
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {
    //@Autowired
    @Resource
    RedisController redisController;
    //@Autowired
    @Resource
    RedisUtil redisUtil;
    public static void main(String[] args) {
        
        TestClass testClass = new TestClass();
        /*redis测试**/
        //redisCtrTest测试
        //testClass.redisCtrTest();
        //redisUtilTest测试
        testClass.redisUtilTest();
    }

    public void redisCtrTest(){
        long start = System.currentTimeMillis();
        System.out.println("redisCtrTest测试开始");
        redisController.setRedis();
        System.out.println("redisCtrTest测试结束,耗时： "+(System.currentTimeMillis()-start)+"ms");
    }

    public void redisUtilTest(){
        long start = System.currentTimeMillis();
        System.out.println("redisUtilTest测试开始");
        redisUtil.set("time",start);
        System.out.println("redisUtilTest测试结束,耗时： "+(System.currentTimeMillis()-start)+"ms");
    }
}
