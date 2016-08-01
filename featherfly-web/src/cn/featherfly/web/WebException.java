
package cn.featherfly.web;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * WebException
 * </p>
 * 
 * @author 钟冀
 */
public class WebException extends LocalizedException{

	private static final long serialVersionUID = -1361906263491038987L;

	/**
	 * 
	 */
	public WebException() {
		super();
	}

	/**
	 * @param message
	 * @param locale
	 * @param ex
	 */
	public WebException(String message, Locale locale, Throwable ex) {
		super(message, locale, ex);
	}

	/**
	 * @param message
	 * @param locale
	 */
	public WebException(String message, Locale locale) {
		super(message, locale);
	}

	/**
	 * @param message
	 * @param argus
	 * @param locale
	 * @param ex
	 */
	public WebException(String message, Object[] argus, Locale locale,
			Throwable ex) {
		super(message, argus, locale, ex);
	}

	/**
	 * @param message
	 * @param argus
	 * @param locale
	 */
	public WebException(String message, Object[] argus, Locale locale) {
		super(message, argus, locale);
	}

	/**
	 * @param message
	 * @param argus
	 * @param ex
	 */
	public WebException(String message, Object[] argus, Throwable ex) {
		super(message, argus, ex);
	}

	/**
	 * @param message
	 * @param argus
	 */
	public WebException(String message, Object[] argus) {
		super(message, argus);
	}

	/**
	 * @param message
	 * @param ex
	 */
	public WebException(String message, Throwable ex) {
		super(message, ex);
	}

	/**
	 * @param message
	 */
	public WebException(String message) {
		super(message);
	}

	/**
	 * @param ex
	 */
	public WebException(Throwable ex) {
		super(ex);
	}

}
