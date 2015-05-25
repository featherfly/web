
package cn.featherfly.web.spring.servlet.view;

import java.util.HashMap;
import java.util.Map;



/**
 * <p>
 * Result
 * </p>
 * 
 * @author 钟冀
 */
public class Result extends HashMap<String, Object>{	

    private static final long serialVersionUID = 7545518859241057552L;
    
    /**
     */
    public Result() {
        setData(new HashMap<String, Object>());
    }
    
	/**
	 * 返回message
	 * @return message
	 */
	public String getMessage() {
		return (String) get("message");
	}

	/**
	 * 设置message
	 * @param message message
	 */
	public void setMessage(String message) {
	    put("message", message);
	}

	/**
	 * 返回status
	 * @return status
	 */
	public Integer getStatus() {
		return (Integer) get("status");
	}

	/**
	 * 设置status
	 * @param status status
	 */
	public void setStatus(Integer status) {
	    put("status", status);
	}

    /**
     * 返回data
     * @return data
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getData() {
        return (Map<String, Object>) get("data");
    }

    /**
     * 设置data
     * @param data data
     */
    public void setData(Map<String, Object> data) {
        put("data", data);
    }
    
    /**
     * 返回data value
     * @param name name
     * @return data value
     */
    @SuppressWarnings("unchecked")
    public <T> T getDataValue(String name) {
        return (T) getData().get(name);
    }
    
    /**
     * 设置data value
     * @param name name
     * @param value value
     */
    public void setDataValue(String name, Object value) {
        getData().put(name, value);
    }
	
	
}
