package cn.featherfly.web.spring.returnvaluehandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import cn.featherfly.common.lang.LangUtils;

public class ResponseBodyWrapFactoryBean implements InitializingBean, ApplicationContextAware {

    private Collection<RequestMappingHandlerAdapter> adapters;

    @Override  
    public void afterPropertiesSet() throws Exception {
        if (LangUtils.isNotEmpty(adapters)) {
            for (RequestMappingHandlerAdapter adapter : adapters) {
                List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
                List<HandlerMethodReturnValueHandler> handlers = new ArrayList<HandlerMethodReturnValueHandler>();
                handlers.addAll(returnValueHandlers);
                decorateHandlers(handlers);
                adapter.setReturnValueHandlers(handlers);
            }
        }
    }
  
    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {  
        for (HandlerMethodReturnValueHandler handler : handlers) {  
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                //用自己的ResponseBody包装类替换掉框架的，达到返回Result的效果  
                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler);  
                int index = handlers.indexOf(handler);  
                handlers.set(index, decorator);
                break;  
            }  
        }  
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        adapters = applicationContext.getBeansOfType(RequestMappingHandlerAdapter.class).values();
    }
}