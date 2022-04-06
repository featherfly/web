
package cn.featherfly.web.upload;

import java.util.Locale;

import cn.featherfly.web.WebException;

/**
 * <p>
 * UploadException
 * </p>
 *
 * @author 钟冀
 */
public class UploadException extends WebException {

    private static final long serialVersionUID = -3703774287662486241L;

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public UploadException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public UploadException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param argus
     * @param locale
     * @param ex
     */
    public UploadException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * @param message
     * @param argus
     * @param locale
     */
    public UploadException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * @param message
     * @param argus
     * @param ex
     */
    public UploadException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * @param message
     * @param argus
     */
    public UploadException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * @param message
     * @param ex
     */
    public UploadException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
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
