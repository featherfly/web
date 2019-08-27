package cn.featherfly.web.servlet;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.StringUtils;

/**
 * <p>
 * ServletUtils
 * </p>
 *
 * @author 钟冀
 */
public class ServletUtils {

	private static final String XML_HTTP_REQUEST_HEADER_NAME = "X-Requested-With";

	private static final String XML_HTTP_REQUEST_HEADER_VALUE = "XMLHttpRequest";

	/**
	 * <p>
	 * 获取请求客户端IP，剥离代理直接找寻原始地址.
	 * </p>
	 * @param request HttpServletRequest
	 * @return 请求客户端IP
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * <p>
	 * 返回请求是否是XMLHttpRequest
	 * </p>
	 * @param request HttpServletRequest
	 * @return 请求是否是XMLHttpRequest
	 */
	public static boolean isXMLHttpRequest(HttpServletRequest request) {
		return XML_HTTP_REQUEST_HEADER_VALUE.equals(
				request.getHeader(XML_HTTP_REQUEST_HEADER_NAME));
	}

	/**
	 * <p>
	 * 获取请求参数字符串
	 * </p>
	 * @param request request
	 * @return 请求参数字符串
	 */
	public static String getParameters(HttpServletRequest request) {
		StringBuilder params = new StringBuilder();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			String[] values = request.getParameterValues(name);
			if (LangUtils.isNotEmpty(values)) {
				for (String value : values) {
					params.append(name).append("=").append(value).append("&");
				}
			}
		}
		if (params.length() > 0) {
			params.deleteCharAt(params.length() - 1);
		}
		return params.toString();
	}

	/**
	 * <p>
	 * 获取请求相对地址，不包含contextPath
	 * </p>
	 * @param request request
	 * @return 请求相对地址
	 */
	public static String getRequestURI(HttpServletRequest request) {
		if (LangUtils.isEmpty(request)) {
			return "";
		}
		String uri = StringUtils.substringBeforeLast(request.getRequestURI(), ".");
		if (!"".equals(request.getContextPath())) {
			uri = StringUtils.substringAfter(uri, request.getContextPath());
		}
		return uri;
	}
}
