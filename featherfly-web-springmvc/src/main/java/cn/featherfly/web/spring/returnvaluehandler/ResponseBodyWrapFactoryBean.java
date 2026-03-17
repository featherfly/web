package cn.featherfly.web.spring.returnvaluehandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityReturnValueHandler;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.policy.AllowDenyListPolicy;
import jakarta.annotation.Resource;

/**
 * response body wrap factory bean.
 *
 * @author zhongj
 */
public class ResponseBodyWrapFactoryBean implements InitializingBean {

    @Resource
    private RequestMappingHandlerAdapter adapter;

    private final AllowDenyListPolicy<Object> returnObjectPolicy;

    private final AllowDenyListPolicy<WebRequest> requestPathPolicy;

    /**
     * Instantiates a new response body wrap factory bean.
     */
    public ResponseBodyWrapFactoryBean() {
        this(new AllowDenyListPolicy<>(AllowDenyListPolicy.Strategy.DENY_ONLY),
            new AllowDenyListPolicy<>(AllowDenyListPolicy.Strategy.DENY_ONLY));
    }

    /**
     * Instantiates a new response body wrap factory bean.
     *
     * @param returnObjectPolicy the return object policy
     * @param requestPathPolicy the request path policy
     */
    public ResponseBodyWrapFactoryBean(AllowDenyListPolicy<Object> returnObjectPolicy,
        AllowDenyListPolicy<WebRequest> requestPathPolicy) {
        this.returnObjectPolicy = returnObjectPolicy;
        this.requestPathPolicy = requestPathPolicy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
        handlers.addAll(returnValueHandlers);
        decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        Class<?> representationModelProcessorHandlerMethodReturnValueHandler = null;
        try {
            representationModelProcessorHandlerMethodReturnValueHandler = ClassUtils.forName(
                "org.springframework.hateoas.server.mvc.RepresentationModelProcessorHandlerMethodReturnValueHandler");
        } catch (Exception e) {
            // 忽略
        }

        Iterator<HandlerMethodReturnValueHandler> itor = handlers.iterator();
        int index = 0;
        while (itor.hasNext()) {
            HandlerMethodReturnValueHandler handler = itor.next();
            if (handler instanceof RequestResponseBodyMethodProcessor
                || handler instanceof ResponseEntityReturnValueHandler
                || ClassUtils.isParent(representationModelProcessorHandlerMethodReturnValueHandler,
                    handler.getClass())) {
                ResponseBodyWrapHandler bodyWrapHandler = new ResponseBodyWrapHandler(handler);
                bodyWrapHandler.setRequestPathPolicy(requestPathPolicy);
                bodyWrapHandler.setReturnObjectPolicy(returnObjectPolicy);
                handlers.set(index, bodyWrapHandler);
            }
            index++;
        }
    }

    /**
     * Gets the request path policy.
     *
     * @return the request path policy
     */
    public AllowDenyListPolicy<WebRequest> getRequestPathPolicy() {
        return requestPathPolicy;
    }

    /**
     * Gets the return object policy.
     *
     * @return the return object policy
     */
    public AllowDenyListPolicy<Object> getReturnObjectPolicy() {
        return returnObjectPolicy;
    }
}
