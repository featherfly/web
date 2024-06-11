package cn.featherfly.web.servlet.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The Class RequestHolderFilter.
 *
 * @author zhongj
 */
public class RequestHolderFilter implements Filter {

    private static final ThreadLocal<HttpServletRequest> holder = new ThreadLocal<>();

    /**
     * Gets the http servlet request.
     *
     * @return the http servlet request
     */
    public static HttpServletRequest getHttpServletRequest() {
        return holder.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        holder.set((HttpServletRequest) request);
        chain.doFilter(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
    }
}
