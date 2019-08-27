
package cn.featherfly.web.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.featherfly.web.servlet.ServletEnv;

/**
 * <p>
 * ServletEnvListener
 * </p>
 *
 * @author 钟冀
 */
public class ServletEnvListener implements ServletContextListener{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletEnv.init(sce.getServletContext());
	}
}
