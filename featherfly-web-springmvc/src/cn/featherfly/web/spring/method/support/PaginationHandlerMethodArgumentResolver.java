
package cn.featherfly.web.spring.method.support;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.structure.page.Pagination;
import cn.featherfly.web.pagination.PaginationFactory;

/**
 * <p>
 * 分页参数
 * </p>
 * 
 * @author 钟冀
 */
public class PaginationHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

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
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AssertIllegalArgument.isNotNull(paginationFactory, "paginationFactory不能为空");
        List<MediaType> mediaTypes = contentNegotiationManager.resolveMediaTypes(webRequest);
        if (ignore(mediaTypes)) {
            return null;
        } else {
            return paginationFactory.create(webRequest.getNativeRequest(HttpServletRequest.class));
        }

    }

    private boolean ignore(List<MediaType> mediaTypes) {
        for (MediaType mediaType : mediaTypes) {
            if (ignore(mediaType)) {
                return true;
            }
        }
        return false;
    }

    private boolean ignore(MediaType mediaType) {
        if (mediaType == null) {
            return true;
        }
        for (MediaType ignoreMediaType : ignoreMediaTypes) {
            if (ignoreMediaType.includes(mediaType)) {
                return true;
            }
        }
        return false;
    }

    private PaginationFactory paginationFactory;

    private ContentNegotiationManager contentNegotiationManager;

    private List<MediaType> ignoreMediaTypes = new ArrayList<>();

    /**
     * 返回paginationFactory
     * 
     * @return paginationFactory
     */
    public PaginationFactory getPaginationFactory() {
        return paginationFactory;
    }

    /**
     * 设置paginationFactory
     * 
     * @param paginationFactory
     *            paginationFactory
     */
    public void setPaginationFactory(PaginationFactory paginationFactory) {
        this.paginationFactory = paginationFactory;
    }

    /**
     * 设置ignoreMediaTypes
     * 
     * @param ignoreMediaTypes
     *            ignoreMediaTypes
     */
    public void setIgnoreMediaTypes(List<String> ignoreMediaTypes) {
        if (LangUtils.isNotEmpty(ignoreMediaTypes)) {
            for (String ignoreMediaType : ignoreMediaTypes) {
                MediaType mediaType = MediaType.valueOf(ignoreMediaType);
                this.ignoreMediaTypes.add(mediaType);
            }
        }
    }

}
