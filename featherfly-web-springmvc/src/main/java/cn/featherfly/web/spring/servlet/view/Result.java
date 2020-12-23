
package cn.featherfly.web.spring.servlet.view;

import cn.featherfly.common.api.Response;

/**
 * <p>
 * Result
 * </p>
 *
 * @author 钟冀
 */
public class Result<D> extends Response<D> {

    private Integer status;

    /**
     */
    public Result() {
        setStatus(1);
    }

    /**
     * 返回status
     *
     * @return status
     * @deprecated use code = OK 代替 status = 1, 其他代码代替错误
     */
    @Deprecated
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置status
     *
     * @param status status
     */
    public void setStatus(Integer status) {
        this.status = status;
        if (status == 1) {
            setCode(SUCCESS_CODE);
        }
    }
}
