
package cn.featherfly.web.spring.servlet.view;




/**
 * <p>
 * Result
 * </p>
 * 
 * @author 钟冀
 */
public class Result<D> {

    private D data;

    private String message;

    private Integer status = 1;
    
    /**
     */
    public Result() {
    }
    
	/**
	 * 返回message
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置message
	 * @param message message
	 */
	public void setMessage(String message) {
	    this.message = message;
	}

	/**
	 * 返回status
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置status
	 * @param status status
	 */
	public void setStatus(Integer status) {
	    this.status = status;
	}

    /**
     * 返回data
     * @return data
     */
    public D getData() {
        return data;
    }

    /**
     * 设置data
     * @param data data
     */
    public void setData(D data) {
        this.data = data;
    }
}
