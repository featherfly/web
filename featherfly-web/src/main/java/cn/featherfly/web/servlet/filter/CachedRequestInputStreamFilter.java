package cn.featherfly.web.servlet.filter;

import java.io.IOException;


#if JAVA_11_OR_LATER
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
#else
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
#endif

/**
 * 请求输入流缓存Filter，用户在一个请求中反复获取输入流
 *
 * @author 钟冀
 */
public class CachedRequestInputStreamFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        chain.doFilter(new CachedRequestStreamHttpServletRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {

    }
}
