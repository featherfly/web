
package cn.featherfly.web.pagination;

import java.util.List;

import cn.featherfly.common.structure.page.Page;

#if JAVA_11_OR_LATER
import jakarta.servlet.http.HttpServletRequest;
#else
import javax.servlet.http.HttpServletRequest;
#endif
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
