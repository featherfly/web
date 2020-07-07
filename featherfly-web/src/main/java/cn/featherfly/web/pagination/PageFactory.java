
package cn.featherfly.web.pagination;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.common.structure.page.Page;

/**
 * <p>
 * PaginationFactory
 * </p>
 *
 * @author 钟冀
 */
public interface PageFactory {
    /**
     * <p>
     * 创建分页对象
     * </p>
     *
     * @param request HttpServletRequest
     * @return 分页对象
     */
    Page create(HttpServletRequest request);
}
