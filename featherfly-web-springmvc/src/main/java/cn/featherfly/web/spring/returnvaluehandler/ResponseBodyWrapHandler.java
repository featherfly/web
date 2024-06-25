package cn.featherfly.web.spring.returnvaluehandler;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.featherfly.common.api.Response;
import cn.featherfly.common.policy.AllowPolicy;
import cn.featherfly.web.spring.servlet.view.Result;

/**
 * ResponseBodyWrapHandler with Result.
 *
 * @author zhongj
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    private AllowPolicy<Object> returnObjectPolicy;

    private AllowPolicy<WebRequest> requestPathPolicy;

    /**
     * Instantiates a new response body wrap handler.
     *
     * @param delegate the delegate
     */
    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest) throws Exception {
        if (returnObjectPolicy != null && !returnObjectPolicy.isAllow(returnValue)
            || requestPathPolicy != null && !requestPathPolicy.isAllow(webRequest)) {
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        } else {
            Result<Object> result = new Result<>();
            result.setData(returnValue);
            result.setCode(Response.SUCCESS_CODE);
            delegate.handleReturnValue(result, returnType, mavContainer, webRequest);
        }
    }

    /**
     * Gets the return object policy.
     *
     * @return the return object policy
     */
    public AllowPolicy<Object> getReturnObjectPolicy() {
        return returnObjectPolicy;
    }

    /**
     * Sets the return object policy.
     *
     * @param returnObjectPolicy the new return object policy
     */
    public void setReturnObjectPolicy(AllowPolicy<Object> returnObjectPolicy) {
        this.returnObjectPolicy = returnObjectPolicy;
    }

    /**
     * Gets the request path policy.
     *
     * @return the request path policy
     */
    public AllowPolicy<WebRequest> getRequestPathPolicy() {
        return requestPathPolicy;
    }

    /**
     * Sets the request path policy.
     *
     * @param requestPathPolicy the new request path policy
     */
    public void setRequestPathPolicy(AllowPolicy<WebRequest> requestPathPolicy) {
        this.requestPathPolicy = requestPathPolicy;
    }
}