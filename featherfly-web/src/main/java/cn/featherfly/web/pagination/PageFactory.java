
package cn.featherfly.web.pagination;

import cn.featherfly.common.structure.page.Page;
import jakarta.servlet.http.HttpServletRequest;

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
