
package test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import sun.util.ResourceBundleEnumeration;

/**
 * <p>
 * DBResourceBundle
 * 类的说明放这里
 * </p>
 * 
 * @author 钟冀
 */
public class DBResourceBundle extends ResourceBundle {

	/**
     * Creates a property resource bundle from an {@link java.io.InputStream
     * InputStream}.  The property file read with this constructor
     * must be encoded in ISO-8859-1.
     *
     * @param stream an InputStream that represents a property file
     *        to read from.
     * @throws IOException if an I/O error occurs
     * @throws NullPointerException if <code>stream</code> is null
     */
    public DBResourceBundle (Properties properties) throws IOException {        
        lookup = new HashMap(properties);
    }

    // Implements java.util.ResourceBundle.handleGetObject; inherits javadoc specification.
    public Object handleGetObject(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return lookup.get(key);
    }

    /**
     * Returns an <code>Enumeration</code> of the keys contained in
     * this <code>ResourceBundle</code> and its parent bundles.
     *
     * @return an <code>Enumeration</code> of the keys contained in
     *         this <code>ResourceBundle</code> and its parent bundles.
     * @see #keySet()
     */
    public Enumeration<String> getKeys() {
        ResourceBundle parent = this.parent;
        return new ResourceBundleEnumeration(lookup.keySet(),
                (parent != null) ? parent.getKeys() : null);
    }

    /**
     * Returns a <code>Set</code> of the keys contained
     * <em>only</em> in this <code>ResourceBundle</code>.
     *
     * @return a <code>Set</code> of the keys contained only in this
     *         <code>ResourceBundle</code>
     * @since 1.6
     * @see #keySet()
     */
    protected Set<String> handleKeySet() {
    	return lookup.keySet();
    }

    // ==================privates====================

    private Map<String,Object> lookup;

}
