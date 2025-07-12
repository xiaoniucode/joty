package cn.xilio.joty.core.security;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.Map;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


/**
 * Spring 工具类，用于提前初始化并高优先级获取容器中的 Bean
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // 设置最高优先级
public class SpringHelper implements ApplicationContextAware {
    private static volatile ApplicationContext applicationContext;
    /**
     * 实现 ApplicationContextAware 接口，Spring 自动注入 ApplicationContext
     *
     * @param applicationContext Spring 应用上下文
     * @throws BeansException 如果注入失败
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringHelper.applicationContext == null) {
            synchronized (SpringHelper.class) { // 线程安全
                if (SpringHelper.applicationContext == null) {
                    SpringHelper.applicationContext = applicationContext;
                }
            }
        }
    }

    /**
     * 在 Bean 初始化后立即检查 ApplicationContext
     */
    @PostConstruct
    public void init() {
        checkApplicationContext(); // 确保 ApplicationContext 已初始化
    }

    /**
     * 获取 ApplicationContext
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 通过 Bean 名称获取 Bean
     *
     * @param name Bean 名称
     * @return Bean 实例
     * @throws BeansException 如果 Bean 不存在
     */
    public static Object getBean(String name) {
        checkApplicationContext();
        return applicationContext.getBean(name);
    }

    /**
     * 通过 Bean 类型获取 Bean
     *
     * @param clazz Bean 类型
     * @param <T>   泛型类型
     * @return Bean 实例
     * @throws BeansException 如果 Bean 不存在
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过 Bean 名称和类型获取 Bean
     *
     * @param name  Bean 名称
     * @param clazz Bean 类型
     * @param <T>   泛型类型
     * @return Bean 实例
     * @throws BeansException 如果 Bean 不存在
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 获取所有指定类型的 Bean
     *
     * @param clazz Bean 类型
     * @param <T>   泛型类型
     * @return Bean 实例的 Map，key 为 Bean 名称，value 为 Bean 实例
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 检查 ApplicationContext 是否已初始化
     *
     * @throws IllegalStateException 如果 ApplicationContext 未初始化
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext 未初始化，请确保 SpringHelper 已注册为 Spring Bean");
        }
    }
}
