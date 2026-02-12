package cn.featherfly.web.servlet;

import java.io.PrintWriter;
import java.io.StringWriter;

#if JAVA_11_OR_LATER
import jakarta.servlet.http.HttpServletResponse;
#else
import javax.servlet.http.HttpServletResponse;
#endif

/**
 * HttpServletResponseWrapper的包装类.
 * <p>
 * 用于将HttpServletResponse的getWriter方法返回的PrintWriter替换为
 * 使用构造方法传入的StringWriter的创建的包装类StringPrintWriter
 * </p>
 *
 * @author 钟冀
 */
public class HttpServletResponseWrapper extends
    #if JAVA_11_OR_LATER jakarta #else javax #endif.servlet.http.HttpServletResponseWrapper {

    private StringPrintWriter pw;

    public HttpServletResponseWrapper(HttpServletResponse response, StringWriter sw) {
        super(response);
        pw = new StringPrintWriter(sw);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PrintWriter getWriter() {
        return pw;
    }
}