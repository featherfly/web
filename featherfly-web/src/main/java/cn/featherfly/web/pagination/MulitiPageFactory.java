
package cn.featherfly.web.pagination;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.common.structure.page.Page;

/**
 * muliti page object factory.
 *
 * @author zhongj
 */
public interface MulitiPageFactory {
    /**
     * create page object list.
     *
     * @param request HttpServletRequest
     * @return page object list
     */
    List<Page> create(HttpServletRequest request);
}
