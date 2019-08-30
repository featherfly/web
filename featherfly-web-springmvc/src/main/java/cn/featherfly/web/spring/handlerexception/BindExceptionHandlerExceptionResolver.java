package cn.featherfly.web.spring.handlerexception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

/**
 * @author Zhong Ji
 */
public class BindExceptionHandlerExceptionResolver extends BindResultHandlerExceptionResolver {

    @Override
    protected BindingResult getBindingResult(Exception ex) {
        if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            return bindException;
        }
        return null;
    }
}
