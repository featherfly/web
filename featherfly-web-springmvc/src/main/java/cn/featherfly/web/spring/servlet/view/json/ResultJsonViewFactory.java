
package cn.featherfly.web.spring.servlet.view.json;

import cn.featherfly.web.spring.servlet.view.Result;

/**
 * MappingJackson2JsonViewFactory.
 *
 * @author zhongj
 */
public class ResultJsonViewFactory {

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
    public ResultJsonView create() {
        ResultJsonView view = new ResultJsonView();
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
    @SuppressWarnings("rawtypes")
    public <R extends Result> ResultJsonView create(R resultObject) {
        ResultJsonView view = new ResultJsonView(resultObject);
        configuration.configure(view.getObjectMapper());
        return view;
    }
}
