package cn.featherfly.web.spring.handlerexception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.featherfly.common.exception.ExceptionCode;
import cn.featherfly.common.exception.ExceptionCodeException;
import cn.featherfly.common.lang.LogUtils;
import cn.featherfly.web.spring.servlet.view.Result;
import cn.featherfly.web.spring.servlet.view.json.ObjectJacksonJsonView;

/**
 * @author Zhong Ji
 */
public class ExceptionHandlerExceptionResolver implements HandlerExceptionResolver, PriorityOrdered {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerExceptionResolver.class);

    private Integer httpStatus = HttpServletResponse.SC_BAD_REQUEST;

    private Map<Class<?>, Integer> exceptionHttpStatusMap = new HashMap<>();

    private int order = 1;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        LogUtils.error(ex, LOG);
        Result<?> result = new Result<>();
        result.setStatus(0);

        response.setStatus(httpStatus);

        String code = null;
        if (ex instanceof ExceptionCodeException) {
            ExceptionCode exceptionCode = ((ExceptionCodeException) ex).getExceptionCode();
            if (exceptionCode != null) {
                code = exceptionCode.getCode();
                result.setCode(exceptionCode.getCode());
            }
        }
        Integer status = exceptionHttpStatusMap.get(ex.getClass());
        if (status != null) {
            response.setStatus(status);
        }
        result.setMessage(ex.getLocalizedMessage());
        LOG.error(code, ex);
        return new ModelAndView(new ObjectJacksonJsonView(result));
    }

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

    /**
     * Getter for property 'exceptionHttpStatusMap'.
     *
     * @return Value for property 'exceptionHttpStatusMap'.
     */
    public Map<Class<?>, Integer> getExceptionHttpStatusMap() {
        return exceptionHttpStatusMap;
    }

    /**
     * Setter for property 'exceptionHttpStatusMap'.
     *
     * @param exceptionHttpStatusMap Value to set for property
     *                               'exceptionHttpStatusMap'.
     */
    public void setExceptionHttpStatusMap(Map<Class<?>, Integer> exceptionHttpStatusMap) {
        this.exceptionHttpStatusMap = exceptionHttpStatusMap;
    }
}
