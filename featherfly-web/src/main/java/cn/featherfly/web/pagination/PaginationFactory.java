
package cn.featherfly.web.pagination;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.common.structure.page.Pagination;

/**
 * <p>
 * PaginationFactory
 * </p>
 * 
 * @author 钟冀
 */
public interface PaginationFactory {
	/**
	 * <p>
	 * 创建分页对象
	 * </p>
	 * @param request HttpServletRequest
	 * @return 分页对象
	 */
	Pagination create(HttpServletRequest request);
}
