
package cn.featherfly.web.spring.servlet.view;

import cn.featherfly.common.api.Response;

/**
 * Result.
 *
 * @author 钟冀
 * @param <D> the generic type
 */
public class Result<D> extends Response<D> {

    /**
     * Instantiates a new result.
     */
    public Result() {
        this(true);
    }

    /**
     * Instantiates a new result.
     *
     * @param success the success
     */
    public Result(boolean success) {
        setSuccess(success);
    }

    /**
     * Sets the success.
     *
     * @param b the new success
     */
    public void setSuccess(boolean b) {
        if (b) {
            setCode(SUCCESS_CODE);
        } else {
            setCode(DEFAULT_ERROR_CODE);
        }
    }
}
