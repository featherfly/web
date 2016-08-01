package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * DatabaseResourceBundleFactory
 * </p>
 * 
 * @author 钟冀
 */
public class DatabaseResourceBundleFactory {
	
	
	public ResourceBundle newBundle(String baseName, Locale locale,
			String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {
		if ((baseName == null) || (locale == null) || (format == null)
				|| (loader == null)) {
			throw new NullPointerException();
		}		
		Properties p = new Properties();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Register the JDBC driver for MySQL.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Define URL of database server for
			// database named db_resource_bundle_article on the localhost
			// with the default port number 3306.
			String url = "jdbc:mysql://localhost:3306/db_resource_bundle_article";
			// Get a connection to the database
			// with an id and password
			con = DriverManager.getConnection(url, "root", "root");
			// Get a Statement object
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			StringBuilder query = new StringBuilder();
			query.append("select string_id, value from resource_bundle where bundle='"
					+ StringEscapeUtils.escapeSql(baseName) + "' ");
			if (locale != null) {
				if (!StringUtils.isEmpty(locale.getCountry())) {
					query.append("and country_code='"
							+ StringEscapeUtils.escapeSql(locale.getCountry())
							+ "' ");
				}
				if (!StringUtils.isEmpty(locale.getLanguage())) {
					query.append("and language_code='"
							+ StringEscapeUtils.escapeSql(locale.getLanguage())
							+ "' ");
				}
				if (!StringUtils.isEmpty(locale.getVariant())) {
					query.append("and variant_code='"
							+ StringEscapeUtils.escapeSql(locale.getVariant())
							+ "' ");
				}
				rs = stmt.executeQuery(query.toString());
				while (rs.next()) {
					p.setProperty(rs.getString(1), rs.getString(2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Can not build properties: " + e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new DBResourceBundle(p);
	}
	
}
