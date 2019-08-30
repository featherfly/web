package cn.featherfly.web.spring.handlerexception;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author Zhong Ji
 */
public class MethodArgumentNotValidExceptionHandlerExceptionResolver extends BindResultHandlerExceptionResolver {

    @Override
    protected BindingResult getBindingResult(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            return exception.getBindingResult();
        }
        return null;
    }
}
