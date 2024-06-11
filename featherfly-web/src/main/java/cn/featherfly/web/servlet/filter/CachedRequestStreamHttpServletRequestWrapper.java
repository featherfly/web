package cn.featherfly.web.servlet.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.web.WebException;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * 请求输入流缓存HttpServletRequestWrapper.
 *
 * @author 钟冀
 */
public class CachedRequestStreamHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private byte[] body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the request
     * @throws IllegalArgumentException if the request is null
     */
    public CachedRequestStreamHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        // 初始化参数
        request.getParameterMap();
        // 初始化请求头
        request.getHeaderNames();
        try {
            body = IOUtils.toByteArray(request.getInputStream());
        } catch (IOException ex) {
            logger.error("Error reading the request body...");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);

        ServletInputStream inputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return getServletInputStream().isFinished();
            }

            @Override
            public boolean isReady() {
                return getServletInputStream().isReady();
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                getServletInputStream().setReadListener(readListener);
            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };

        return inputStream;
    }

    private ServletInputStream getServletInputStream() {
        try {
            return getInputStream();
        } catch (IOException e) {
            throw new WebException("Error getInputStream...", e);
        }
    }
}
