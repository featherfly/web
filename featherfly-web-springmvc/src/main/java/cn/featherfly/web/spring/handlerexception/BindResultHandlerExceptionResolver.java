package cn.featherfly.web.spring.handlerexception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.PriorityOrdered;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.featherfly.web.spring.servlet.view.Result;
import cn.featherfly.web.spring.servlet.view.json.ObjectJacksonJsonView;

/**
 * @author Zhong Ji
 */
public abstract class BindResultHandlerExceptionResolver implements HandlerExceptionResolver, PriorityOrdered {

    private Integer httpStatus = HttpServletResponse.SC_BAD_REQUEST;

    private int order = 0;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        BindingResult bindingResult = getBindingResult(ex);
        if (bindingResult != null) {
            Result<?> result = new Result<>();
            StringBuilder messages = new StringBuilder();
            if (bindingResult.hasErrors()) {
                result.setStatus(0);
            }
            if (bindingResult.hasGlobalErrors()) {
                for (ObjectError oe : bindingResult.getGlobalErrors()) {
                    messages.append(oe.getDefaultMessage()).append("，");
                }
            }
            if (bindingResult.hasFieldErrors()) {

                for (org.springframework.validation.FieldError fr : bindingResult.getFieldErrors()) {
                    messages.append(fr.getDefaultMessage()).append("，");
                }
            }
            if (messages.length() > 0) {
                messages.deleteCharAt(messages.length() - 1);
            }
            result.setMessage(messages.toString());
            response.setStatus(httpStatus);
            return new ModelAndView(new ObjectJacksonJsonView(result));
        }
        return null;
    }

    protected abstract BindingResult getBindingResult(Exception ex);

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}
