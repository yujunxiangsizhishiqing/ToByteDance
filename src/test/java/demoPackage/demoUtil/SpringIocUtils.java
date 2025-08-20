package demoPackage.demoUtil;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 从springIOC容器中获取对象
 *
 * @author wx
 * @date 2024-08-21
 **/
@Component
public class SpringIocUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取applicationContext对象
     *
     * @return ApplicationContext
     * @author wx
     * @date 2024-08-21
     **/
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringIocUtils.applicationContext == null) {
            SpringIocUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 通过name获取 Bean.
     *
     * @author wx
     * @date 2024-08-21
     **/
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @author wx
     * @date 2024-08-21
     **/
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @author wx
     * @date 2024-08-21
     **/
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}

