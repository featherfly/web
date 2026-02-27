
package cn.featherfly.web.spring.servlet.view;

import cn.featherfly.common.api.Response;

/**
 * Result.
 *
 * @param <D> the generic type
 * @author zhongj
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
     * Success result.
     *
     * @return the result
     */
    public static Result success() {
        return new Result(true);
    }

    /**
     * error result.
     *
     * @return the result
     */
    public static Result error() {
        return new Result(false);
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
