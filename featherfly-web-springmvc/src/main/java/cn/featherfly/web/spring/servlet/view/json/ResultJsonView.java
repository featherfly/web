
package cn.featherfly.web.spring.servlet.view.json;

import java.util.Map;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.web.spring.servlet.view.Result;

/**
 * <p>
 * ResultJsonView
 * </p>
 *
 * @author 钟冀
 */
public class ResultJsonView extends ObjectJacksonJsonView {

    /**
     */
    public ResultJsonView() {
    }

    /**
     * @param message message
     */
    public ResultJsonView(String message) {
        this(null, message);
    }

    /**
     * @param resultObject resultObject
     */
    @SuppressWarnings("rawtypes")
    public <R extends Result> ResultJsonView(R resultObject) {
        this(resultObject, null);
    }

    /**
     * @param resultObject resultObject
     * @param message      message
     */
    @SuppressWarnings("rawtypes")
    public <R extends Result> ResultJsonView(R resultObject, String message) {
        super(resultObject);
        this.message = message;
    }

    /**
     * return result
     *
     * @param model the model, as passed on to {@link #renderMergedOutputModel}
     * @return the value to be rendered -&gt; resultObject
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected Object filterModel(Map<String, Object> model) {
        Result result = (Result) getResult();
        if (result == null) {
            result = new Result();
        }
        result.setStatus(1);
        if (Lang.isNotEmpty(message)) {
            result.setMessage(message);
        }
        //		if (Lang.isEmpty(result.getMessage())) {
        //		    result.setMessage("调用成功");
        //		}
        return result;
    }

    private String message;

    /**
     * 返回message
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
