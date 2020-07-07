package cn.featherfly.web.spring.returnvaluehandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import cn.featherfly.common.lang.ClassUtils;

public class ResponseBodyWrapFactoryBean implements InitializingBean {

    @Resource
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
        handlers.addAll(returnValueHandlers);
        decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        Iterator<HandlerMethodReturnValueHandler> var2 = handlers.iterator();
        while (var2.hasNext()) {
            HandlerMethodReturnValueHandler handler = var2.next();
            Class<?> representationModelProcessorHandlerMethodReturnValueHandler = null;
            try {
                representationModelProcessorHandlerMethodReturnValueHandler = ClassUtils.forName(
                        "org.springframework.hateoas.server.mvc.RepresentationModelProcessorHandlerMethodReturnValueHandler");
            } catch (Exception e) {
                // 忽略
            }
            if (handler instanceof RequestResponseBodyMethodProcessor || ClassUtils
                    .isParent(representationModelProcessorHandlerMethodReturnValueHandler, handler.getClass())) {
                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler);
                int index = handlers.indexOf(handler);
                handlers.set(index, decorator);
                break;
            }
        }
    }
}