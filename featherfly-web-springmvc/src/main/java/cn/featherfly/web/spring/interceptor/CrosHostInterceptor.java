package cn.featherfly.web.spring.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import cn.featherfly.common.constant.Chars;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * CrosHostInterceptor.
 *
 * @author zhongj
 */
@Component
public class CrosHostInterceptor implements AsyncHandlerInterceptor {

    /**
     */
    public CrosHostInterceptor() {
        List<String> _allowOrigin = new ArrayList<>();
        _allowOrigin.add("*");
        setAllowOrigin(_allowOrigin);

        List<String> _allowHeaders = new ArrayList<>();
        _allowHeaders.add("X-Requested-With");
        _allowHeaders.add("content-type");
        _allowHeaders.add("token");
        setAllowHeaders(_allowHeaders);

        List<String> _allowMethods = new ArrayList<>();
        _allowMethods.add("GET");
        _allowMethods.add("HEAD");
        _allowMethods.add("POST");
        _allowMethods.add("PUT");
        _allowMethods.add("DELETE");
        _allowMethods.add("TRACE");
        _allowMethods.add("OPTIONS");
        _allowMethods.add("PATCH");
        setAllowMethods(_allowMethods);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        //添加跨域CORS
        //        response.setHeader("Access-Control-Allow-Origin", "*");
        //        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
        //        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        //        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Origin", allowOriginStr);
        response.setHeader("Access-Control-Allow-Headers", allowHeadersStr);
        response.setHeader("Access-Control-Allow-Methods", allowMethodsStr);
        response.setHeader("Access-Control-Max-Age", maxAge + "");

        return true;
    }

    private String toString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String string : list) {
            sb.append(string).append(Chars.COMMA);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private String allowOriginStr;
    private String allowHeadersStr;
    private String allowMethodsStr;

    private List<String> allowOrigin;
    private List<String> allowHeaders;
    private List<String> allowMethods;
    private int maxAge = 3600;

    /**
     * get allowOrigin value
     *
     * @return allowOrigin
     */
    public List<String> getAllowOrigin() {
        return allowOrigin;
    }

    /**
     * set allowOrigin value
     *
     * @param allowOrigin allowOrigin
     */
    public void setAllowOrigin(List<String> allowOrigin) {
        this.allowOrigin = allowOrigin;
        allowOriginStr = toString(allowOrigin);
    }

    /**
     * get allowHeaders value
     *
     * @return allowHeaders
     */
    public List<String> getAllowHeaders() {
        return allowHeaders;
    }

    /**
     * set allowHeaders value
     *
     * @param allowHeaders allowHeaders
     */
    public void setAllowHeaders(List<String> allowHeaders) {
        this.allowHeaders = allowHeaders;
        allowHeadersStr = toString(allowHeaders);
    }

    /**
     * get allowMethods value
     *
     * @return allowMethods
     */
    public List<String> getAllowMethods() {
        return allowMethods;
    }

    /**
     * set allowMethods value
     *
     * @param allowMethods allowMethods
     */
    public void setAllowMethods(List<String> allowMethods) {
        this.allowMethods = allowMethods;
        allowMethodsStr = toString(allowMethods);
    }

    /**
     * get maxAge value
     *
     * @return maxAge
     */
    public int getMaxAge() {
        return maxAge;
    }

    /**
     * set maxAge value
     *
     * @param maxAge maxAge
     */
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}