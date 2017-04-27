package cn.featherfly.web.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * HttpCache
 * </p>
 * 
 * @author zhongj
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpCache {

    /**
     * 超时时间，单位秒，默认1分钟
     * @return 超时时间 
     */
    long expire() default 60L;
}
