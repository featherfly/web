
package cn.featherfly.web.spring.method.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.structure.page.Pagination;
import cn.featherfly.web.pagination.PaginationFactory;

/**
 * <p>
 * T
 * 类的说明放这里
 * </p>
 * 
 * @author 钟冀
 */
public class PaginationHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return ClassUtils.isParent(Pagination.class, parameter.getParameterType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		AssertIllegalArgument.isNotNull(paginationFactory, "paginationFactory不能为空");
		return paginationFactory.create(webRequest.getNativeRequest(HttpServletRequest.class));
	}
	
	private PaginationFactory paginationFactory;

	/**
	 * 返回paginationFactory
	 * @return paginationFactory
	 */
	public PaginationFactory getPaginationFactory() {
		return paginationFactory;
	}

	/**
	 * 设置paginationFactory
	 * @param paginationFactory paginationFactory
	 */
	public void setPaginationFactory(PaginationFactory paginationFactory) {
		this.paginationFactory = paginationFactory;
	}

}
