
package cn.featherfly.web.servlet.listener;

import cn.featherfly.web.servlet.ServletEnv;

#if JAVA_11_OR_LATER
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
#else
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
#endif

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
