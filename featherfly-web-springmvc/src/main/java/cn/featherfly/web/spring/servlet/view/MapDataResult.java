
package cn.featherfly.web.spring.servlet.view;

import java.util.HashMap;
import java.util.Map;

/**
 * Map Data Result.
 *
 * @author zhongj
 */
public class MapDataResult extends Result<Map<String, Object>> {

    /**
     * Instantiates a new map data result.
     */
    public MapDataResult() {
        setData(new HashMap<String, Object>());
    }

    /**
     * 返回data value.
     *
     * @param <T>  the generic type
     * @param name name
     * @return data value
     */
    @SuppressWarnings("unchecked")
    public <T> T getDataValue(String name) {
        return (T) getData().get(name);
    }

    /**
     * 设置data value.
     *
     * @param name  name
     * @param value value
     */
    public void setDataValue(String name, Object value) {
        getData().put(name, value);
    }
}
