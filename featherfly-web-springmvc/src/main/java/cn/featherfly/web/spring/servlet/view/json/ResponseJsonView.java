
package cn.featherfly.web.spring.servlet.view.json;

import java.util.Map;

import cn.featherfly.common.api.Response;
import cn.featherfly.common.lang.Lang;

/**
 * Result Json View.
 *
 * @author zhongj
 */
public class ResponseJsonView extends ObjectJacksonJsonView {

    /**
     * Instantiates a new result json view.
     */
    public ResponseJsonView() {
    }

    /**
     * Instantiates a new result json view.
     *
     * @param message message
     */
    public ResponseJsonView(String message) {
        this(null, message);
    }

    /**
     * Instantiates a new result json view.
     *
     * @param <R> the generic type
     * @param resultObject resultObject
     */
    public <R extends Response<?>> ResponseJsonView(R resultObject) {
        this(resultObject, null);
    }

    /**
     * Instantiates a new result json view.
     *
     * @param <R> the generic type
     * @param resultObject resultObject
     * @param message message
     */
    public <R extends Response<?>> ResponseJsonView(R resultObject, String message) {
        super(resultObject);
        this.message = message;
    }

    /**
     * return result.
     *
     * @param model the model, as passed on to {@link #renderMergedOutputModel}
     * @return the value to be rendered -&gt; resultObject
     */
    @Override
    protected Object filterModel(Map<String, Object> model) {
        Response<?> res = (Response<?>) getResult();
        if (res == null) {
            res = new Response<>();
        }
        if (Lang.isNotEmpty(message)) {
            res.setMessage(message);
        }
        return res;
    }

    private String message;

    /**
     * 返回message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message.
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
