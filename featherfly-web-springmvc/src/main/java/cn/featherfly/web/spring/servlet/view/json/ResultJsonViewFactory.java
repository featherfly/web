
package cn.featherfly.web.spring.servlet.view.json;

import cn.featherfly.web.spring.servlet.view.Result;

/**
 * <p>
 * MappingJackson2JsonViewFactory
 * </p>
 * 
 * @author 钟冀
 */
public class ResultJsonViewFactory {

   
    private ObjectMapperConfiguration configuration;
   
    /**
     * 返回configuration
     * @return configuration
     */
    public ObjectMapperConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * 设置configuration
     * @param configuration configuration
     */
    public void setConfiguration(ObjectMapperConfiguration configuration) {
        this.configuration = configuration;
    }

    public ResultJsonView create() {
        ResultJsonView view = new ResultJsonView();
        configuration.configure(view.getObjectMapper());
        return view;
    }
    @SuppressWarnings("rawtypes")
    public <R extends Result> ResultJsonView create(R resultObject) {
        ResultJsonView view = new ResultJsonView(resultObject);
        configuration.configure(view.getObjectMapper());
        return view;
    }
}
