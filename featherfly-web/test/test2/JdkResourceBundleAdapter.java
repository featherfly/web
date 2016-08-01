package test2;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import sun.util.ResourceBundleEnumeration;

/**
 * <p>
 * SimpleResourceBundle
 * </p>
 * 
 * @author 钟冀
 */
public class JdkResourceBundleAdapter extends java.util.ResourceBundle implements test2.ResourceBundle {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JdkResourceBundleAdapter(Properties properties) {
		lookup = new HashMap(properties);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}
		return lookup.get(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumeration<String> getKeys() {
		ResourceBundle parent = this.parent;
		return new ResourceBundleEnumeration(lookup.keySet(),
				(parent != null) ? parent.getKeys() : null);
	}

	private Map<String, Object> lookup;

}
