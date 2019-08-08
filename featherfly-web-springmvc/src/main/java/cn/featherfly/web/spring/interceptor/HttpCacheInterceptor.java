package cn.featherfly.web.spring.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.featherfly.web.spring.annotation.HttpCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * HttpCacheInterceptor
 * </p>
 * 
 * @author zhongj
 */
public class HttpCacheInterceptor extends HandlerInterceptorAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        HandlerMethod invokedMethod = (HandlerMethod) handler;
        HttpCache httpCache = invokedMethod.getMethod().getAnnotation(HttpCache.class);
        if (httpCache != null) {
            long expireTime = httpCache.expire();
            if (expireTime > 0) {
                response.addHeader("Cache-Control", "max-age=" + expireTime);
            } else {
                response.addHeader("Pragma", "no-cache");
                response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
                response.addDateHeader("Expires", 1L);
            }
        }
    }
}