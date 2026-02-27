
package cn.featherfly.web.spring.method.support;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.structure.page.Page;
import cn.featherfly.web.WebException;
import cn.featherfly.web.pagination.PageFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
#if JAVA_11_OR_LATER
import jakarta.servlet.http.HttpServletRequest;
#else
import javax.servlet.http.HttpServletRequest;
#endif

/**
 * 分页参数.
 *
 * @author zhongj
 */
public class PageHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public PageHandlerMethodArgumentResolver() {
        mediaTypesResolver = new Function<NativeWebRequest, List<MediaType>>() {
            private ContentNegotiationStrategy contentNegotiationStrategy  = new HeaderContentNegotiationStrategy();

            @Override
            public List<MediaType> apply(NativeWebRequest webRequest) {
                try {
                    return contentNegotiationStrategy.resolveMediaTypes(webRequest);
                } catch (HttpMediaTypeNotAcceptableException e) {
                    throw new WebException(e);
                }
            }
        };
    }

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
        List<MediaType> mediaTypes = mediaTypesResolver.apply(webRequest);
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

    private List<MediaType> ignoreMediaTypes = new ArrayList<>();

    private Function<NativeWebRequest, List<MediaType>> mediaTypesResolver = null;


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

    public void setMediaTypesResolver(Function<NativeWebRequest, List<MediaType>> mediaTypesResolver) {
        this.mediaTypesResolver = mediaTypesResolver;
    }
}
