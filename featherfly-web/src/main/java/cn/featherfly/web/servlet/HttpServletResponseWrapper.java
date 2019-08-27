package cn.featherfly.web.servlet;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * HttpServletResponseWrapper的包装类.
 * 用于将HttpServletResponse的getWriter方法返回的PrintWriter替换为
 * 使用构造方法传入的StringWriter的创建的包装类StringPrintWriter
 * </p>
 *
 * @author 钟冀
 */
public class HttpServletResponseWrapper extends javax.servlet.http.HttpServletResponseWrapper{

	private StringPrintWriter pw;

	public HttpServletResponseWrapper(HttpServletResponse response,StringWriter sw){
		super(response);
		this.pw = new StringPrintWriter(sw);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrintWriter getWriter(){
		return pw;
	}
}