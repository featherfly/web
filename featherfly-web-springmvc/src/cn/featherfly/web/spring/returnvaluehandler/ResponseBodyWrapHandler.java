package cn.featherfly.web.spring.returnvaluehandler;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.featherfly.web.spring.servlet.view.Result;

/**
 * ResponseBodyWrapHandler with Result
 * @author Zhong Ji
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler{

    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate){
        this.delegate=delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws Exception {
        // TODO 这里以后要设置白黑名单
        Result<Object> result = new Result<>();
        result.setData(returnValue);
        delegate.handleReturnValue(result, returnType, mavContainer, webRequest);
    }
}