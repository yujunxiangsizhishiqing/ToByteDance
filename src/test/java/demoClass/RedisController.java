package demoClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class RedisController {

    //引入 redis
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 存储
     * @return
     */
    @RequestMapping("/set")
    public String setRedis() {
        //存储 key-value 键值对
        stringRedisTemplate.opsForValue().set("username", "wangxu");
        return "redis 存储成功！";
    }

    /**
     * 读取
     * @return
     */
    @RequestMapping("/get")
    public String getRedis() {
        //通过 key 值读取 value
        String result = stringRedisTemplate.opsForValue().get("username");
        return result;
    }

}
