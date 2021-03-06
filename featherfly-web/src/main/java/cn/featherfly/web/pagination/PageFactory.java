
package cn.featherfly.web.pagination;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.common.structure.page.Page;

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
