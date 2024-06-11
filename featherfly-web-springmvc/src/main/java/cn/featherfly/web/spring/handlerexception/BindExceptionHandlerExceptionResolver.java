package cn.featherfly.web.spring.handlerexception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

/**
 * The Class BindExceptionHandlerExceptionResolver.
 *
 * @author zhongj
 */
public class BindExceptionHandlerExceptionResolver extends BindResultHandlerExceptionResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    protected BindingResult getBindingResult(Exception ex) {
        if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            return bindException;
        }
        return null;
    }
}
