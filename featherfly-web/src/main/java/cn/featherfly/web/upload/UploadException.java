
package cn.featherfly.web.upload;

import java.util.Locale;

import cn.featherfly.web.WebException;

/**
 * UploadException.
 *
 * @author 钟冀
 */
public class UploadException extends WebException {

    private static final long serialVersionUID = -3703774287662486241L;

    /**
     * Instantiates a new upload exception.
     *
     * @param message the message
     * @param locale the locale
     * @param ex the ex
     */
    public UploadException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new upload exception.
     *
     * @param message the message
     * @param locale the locale
     */
    public UploadException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new upload exception.
     *
     * @param message the message
     * @param argus the argus
     * @param locale the locale
     * @param ex the ex
     */
    public UploadException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * Instantiates a new upload exception.
     *
     * @param message the message
     * @param argus the argus
     * @param locale the locale
     */
    public UploadException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * Instantiates a new upload exception.
     *
     * @param message the message
     * @param argus the argus
     * @param ex the ex
     */
    public UploadException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * Instantiates a new upload exception.
     *
     * @param message the message
     * @param argus the argus
     */
    public UploadException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * Instantiates a new upload exception.
     *
     * @param message the message
     * @param ex the ex
     */
    public UploadException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new upload exception.
     *
     * @param message the message
     */
    public UploadException(String message) {
        super(message);
    }

    /**
     * Instantiates a new upload exception.
     *
     * @param ex the ex
     */
    public UploadException(Throwable ex) {
        super(ex);
    }

}
