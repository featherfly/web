
package cn.featherfly.web.spring.servlet.view.json;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * <p>
 * MappingJackson2JsonViewFactory
 * </p>
 * 
 * @author 钟冀
 */
public class MappingJackson2JsonViewFactory {

   
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

    public MappingJackson2JsonView create() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        configuration.configure(view.getObjectMapper());
        return view;
    }
}
