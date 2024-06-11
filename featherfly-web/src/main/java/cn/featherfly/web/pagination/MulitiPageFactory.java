
package cn.featherfly.web.pagination;

import java.util.List;

import cn.featherfly.common.structure.page.Page;
import jakarta.servlet.http.HttpServletRequest;

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
