
package cn.featherfly.web.spring.servlet.view.json;


/**
 * <p>
 * MappingJackson2JsonViewFactory
 * </p>
 * 
 * @author 钟冀
 */
public class ObjectJacksonJsonViewFactory {

   
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

    public ObjectJacksonJsonView create() {
        ObjectJacksonJsonView view = new ObjectJacksonJsonView();
        configuration.configure(view.getObjectMapper());
        return view;
    }
    public  ObjectJacksonJsonView create(Object object) {
        ObjectJacksonJsonView view = new ObjectJacksonJsonView(object);
        configuration.configure(view.getObjectMapper());
        return view;
    }
}
