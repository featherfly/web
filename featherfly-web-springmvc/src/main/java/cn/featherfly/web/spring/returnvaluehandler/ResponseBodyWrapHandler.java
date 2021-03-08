package cn.featherfly.web.spring.returnvaluehandler;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.featherfly.common.api.Response;
import cn.featherfly.common.policy.AllowPolicy;
import cn.featherfly.web.spring.servlet.view.Result;

/**
 * ResponseBodyWrapHandler with Result
 *
 * @author Zhong Ji
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    private AllowPolicy<Object> allowPolicy;

    private boolean onlyWrapNull;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws Exception {
        if (allowPolicy != null && !allowPolicy.isAllow(returnValue)) {
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        } else {
            if (returnValue == null || !onlyWrapNull) {
                Result<Object> result = new Result<>();
                result.setData(returnValue);
                result.setCode(Response.SUCCESS_CODE);
                delegate.handleReturnValue(result, returnType, mavContainer, webRequest);
            }
        }
    }

    /**
     * 返回allowPolicy
     *
     * @return allowPolicy
     */
    public AllowPolicy<Object> getAllowPolicy() {
        return allowPolicy;
    }

    /**
     * 设置allowPolicy
     *
     * @param allowPolicy allowPolicy
     */
    public void setAllowPolicy(AllowPolicy<Object> allowPolicy) {
        this.allowPolicy = allowPolicy;
    }

    /**
     * get onlyWrapNull value
     *
     * @return onlyWrapNull
     */
    public boolean isOnlyWrapNull() {
        return onlyWrapNull;
    }

    /**
     * set onlyWrapNull value
     *
     * @param onlyWrapNull onlyWrapNull
     */
    public void setOnlyWrapNull(boolean onlyWrapNull) {
        this.onlyWrapNull = onlyWrapNull;
    }

}