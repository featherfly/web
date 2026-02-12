
package cn.featherfly.web.pagination;

import cn.featherfly.common.structure.page.Page;
#if JAVA_11_OR_LATER
import jakarta.servlet.http.HttpServletRequest;
#else
import javax.servlet.http.HttpServletRequest;
#endif

/**
 * page object factory.
 *
 * @author zhongj
 */
public interface PageFactory {
    /**
     * create page object.
     *
     * @param request HttpServletRequest
     * @return page object
     */
    Page create(HttpServletRequest request);
}
