
package cn.featherfly.web.servlet.listener;

import cn.featherfly.web.servlet.ServletEnv;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * ServletEnvListener.
 *
 * @author 钟冀
 */
public class ServletEnvListener implements ServletContextListener {

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
