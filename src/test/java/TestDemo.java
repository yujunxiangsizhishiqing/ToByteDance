
import com.mashibing.javabackend.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestDemo {
    @Autowired
    private RedisUtil redisUtil;

    public static void main(String[] args) {
        System.out.println("TestDemo");
    }

    @org.junit.Test
    public void redisTest(){
        System.out.println("redisTest");
    }
}
