
package cn.featherfly.web.spring.servlet.view.json;

/**
 * MappingJackson2JsonViewFactory.
 *
 * @author zhongj
 */
public class ObjectJacksonJsonViewFactory {

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
     * @return the object jackson json view
     */
    public ObjectJacksonJsonView create() {
        ObjectJacksonJsonView view = new ObjectJacksonJsonView();
        configuration.configure(view.getObjectMapper());
        return view;
    }

    /**
     * Creates the.
     *
     * @param object the object
     * @return the object jackson json view
     */
    public ObjectJacksonJsonView create(Object object) {
        ObjectJacksonJsonView view = new ObjectJacksonJsonView(object);
        configuration.configure(view.getObjectMapper());
        return view;
    }
}
