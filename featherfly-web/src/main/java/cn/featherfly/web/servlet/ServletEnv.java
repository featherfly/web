
package cn.featherfly.web.servlet;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.Assert;
import cn.featherfly.common.lang.UriUtils;
import cn.featherfly.web.WebException;
import jakarta.servlet.ServletContext;

/**
 * servlet运行环境.
 *
 * @author 钟冀
 */
public class ServletEnv {

    private static final Assert<WebException> ASSERT = new Assert<>((message) -> new WebException(message));

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletEnv.class);

    private static final String WEBINF_PATH = "WEB-INF";

    private static final String CLASSES_PATH = WEBINF_PATH + "/classes";

    private static final String LIB_PATH = WEBINF_PATH + "/lib";

    private static final ServletEnv env = new ServletEnv();

    /**
     * 初始化.
     *
     * @param servletContext ServletContext
     */
    public static void init(ServletContext servletContext) {
        env.projectDir = servletContext.getRealPath("/");
        LOGGER.debug("项目根目录：{}", env.projectDir);
        env.webinfDir = new File(UriUtils.linkUri(env.projectDir + WEBINF_PATH)).getAbsolutePath();
        LOGGER.debug("项目WEB-INF目录：{}", env.webinfDir);
        env.classesDir = new File(UriUtils.linkUri(env.projectDir + CLASSES_PATH)).getAbsolutePath();
        LOGGER.debug("项目classes目录：{}", env.classesDir);
        env.libDir = new File(UriUtils.linkUri(env.projectDir + LIB_PATH)).getAbsolutePath();
        LOGGER.debug("项目lib目录：{}", env.libDir);
    }

    /**
     * <p>
     * 获取ServletEnv
     * </p>
     * .
     *
     * @return the env
     */
    public static ServletEnv getEnv() {
        ASSERT.isNotEmpty(env.projectDir, "ServletEnv还没有初始化");
        return env;
    }

    private String projectDir;

    private String classesDir;

    private String webinfDir;

    private String libDir;

    /**
     * 返回projectDir.
     *
     * @return projectDir
     */
    public String getProjectDir() {
        return projectDir;
    }

    /**
     * 返回classesDir.
     *
     * @return classesDir
     */
    public String getClassesDir() {
        return classesDir;
    }

    /**
     * 返回webinfDir.
     *
     * @return webinfDir
     */
    public String getWebinfDir() {
        return webinfDir;
    }

    /**
     * 返回libDir.
     *
     * @return libDir
     */
    public String getLibDir() {
        return libDir;
    }
}
