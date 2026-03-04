
package cn.featherfly.web.spring.servlet.view.json;

import cn.featherfly.common.api.Response;

/**
 * MappingJackson2JsonViewFactory.
 *
 * @author zhongj
 */
public class ResponseJsonViewFactory {

    private ObjectMapperConfiguration configuration;

    /**
     * 返回configuration.
     *
     * @return configuration
     */
    public ObjectMapperConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * 设置configuration.
     *
     * @param configuration configuration
     */
    public void setConfiguration(ObjectMapperConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Creates the.
     *
     * @return the result json view
     */
    public ResponseJsonView create() {
        ResponseJsonView view = new ResponseJsonView();
        configuration.configure(view.getObjectMapper());
        return view;
    }

    /**
     * Creates the.
     *
     * @param <R> the generic type
     * @param resultObject the result object
     * @return the result json view
     */
    public <R extends Response<?>> ResponseJsonView create(R resultObject) {
        ResponseJsonView view = new ResponseJsonView(resultObject);
        configuration.configure(view.getObjectMapper());
        return view;
    }
}
