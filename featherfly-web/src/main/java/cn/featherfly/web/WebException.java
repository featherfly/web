
package cn.featherfly.web;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * WebException.
 *
 * @author zhongj
 */
public class WebException extends LocalizedException {

    private static final long serialVersionUID = -1361906263491038987L;

    /**
     * Instantiates a new web exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public WebException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new web exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public WebException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new web exception.
     *
     * @param message the message
     * @param argus   the argus
     * @param locale  the locale
     * @param ex      the ex
     */
    public WebException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * Instantiates a new web exception.
     *
     * @param message the message
     * @param argus   the argus
     * @param locale  the locale
     */
    public WebException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * Instantiates a new web exception.
     *
     * @param message the message
     * @param argus   the argus
     * @param ex      the ex
     */
    public WebException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * Instantiates a new web exception.
     *
     * @param message the message
     * @param argus   the argus
     */
    public WebException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * Instantiates a new web exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public WebException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new web exception.
     *
     * @param message the message
     */
    public WebException(String message) {
        super(message);
    }

    /**
     * Instantiates a new web exception.
     *
     * @param ex the ex
     */
    public WebException(Throwable ex) {
        super(ex);
    }

}
