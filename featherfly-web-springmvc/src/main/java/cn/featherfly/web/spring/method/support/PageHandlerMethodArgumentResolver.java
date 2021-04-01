
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
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.structure.page.Page;
import cn.featherfly.web.pagination.PageFactory;

/**
 * <p>
 * 分页参数
 * </p>
 *
 * @author 钟冀
 */
public class PageHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ClassUtils.isParent(Page.class, parameter.getParameterType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AssertIllegalArgument.isNotNull(pageFactory, "pageFacotry不能为空");
        List<MediaType> mediaTypes = contentNegotiationManager.resolveMediaTypes(webRequest);
        if (ignore(mediaTypes)) {
            return null;
        } else {
            return pageFactory.create(webRequest.getNativeRequest(HttpServletRequest.class));
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

    private PageFactory pageFactory;

    private ContentNegotiationManager contentNegotiationManager;

    private List<MediaType> ignoreMediaTypes = new ArrayList<>();

    /**
     * 返回pageFacotry
     *
     * @return pageFacotry
     */
    public PageFactory getPageFactory() {
        return pageFactory;
    }

    /**
     * 设置pageFacotry
     *
     * @param pageFactory pageFacotry
     */
    public void setPageFactory(PageFactory pageFactory) {
        this.pageFactory = pageFactory;
    }

    /**
     * 设置ignoreMediaTypes
     *
     * @param ignoreMediaTypes ignoreMediaTypes
     */
    public void setIgnoreMediaTypes(List<String> ignoreMediaTypes) {
        if (Lang.isNotEmpty(ignoreMediaTypes)) {
            for (String ignoreMediaType : ignoreMediaTypes) {
                MediaType mediaType = MediaType.valueOf(ignoreMediaType);
                this.ignoreMediaTypes.add(mediaType);
            }
        }
    }

    /**
     * 设置contentNegotiationManager
     *
     * @param contentNegotiationManager contentNegotiationManager
     */
    public void setContentNegotiationManager(ContentNegotiationManager contentNegotiationManager) {
        this.contentNegotiationManager = contentNegotiationManager;
    }

}
