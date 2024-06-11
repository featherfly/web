package cn.featherfly.web.spring.handlerexception;

import org.springframework.core.PriorityOrdered;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.featherfly.common.api.Response;
import cn.featherfly.web.spring.servlet.view.Result;
import cn.featherfly.web.spring.servlet.view.json.ObjectJacksonJsonView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The Class BindResultHandlerExceptionResolver.
 *
 * @author zhongj
 */
public abstract class BindResultHandlerExceptionResolver implements HandlerExceptionResolver, PriorityOrdered {

    private Integer httpStatus = HttpServletResponse.SC_BAD_REQUEST;

    private int order = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) {
        BindingResult bindingResult = getBindingResult(ex);
        if (bindingResult != null) {
            Result<?> result = new Result<>();
            StringBuilder messages = new StringBuilder();
            if (bindingResult.hasErrors()) {
                result.setCode(Response.DEFAULT_ERROR_CODE);
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

    /**
     * Gets the binding result.
     *
     * @param ex the ex
     * @return the binding result
     */
    protected abstract BindingResult getBindingResult(Exception ex);

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        return order;
    }

    /**
     * Sets the order.
     *
     * @param order the new order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets the http status.
     *
     * @return the http status
     */
    public Integer getHttpStatus() {
        return httpStatus;
    }

    /**
     * Sets the http status.
     *
     * @param httpStatus the new http status
     */
    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}
