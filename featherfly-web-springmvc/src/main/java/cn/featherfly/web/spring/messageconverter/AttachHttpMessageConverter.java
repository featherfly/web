
package cn.featherfly.web.spring.messageconverter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.lang.UriUtils;
import cn.featherfly.web.WebException;
import cn.featherfly.web.servlet.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;

/**
 * JxlsHttpMessageConverter.
 *
 * @author zhongj
 */
public abstract class AttachHttpMessageConverter extends AbstractGenericHttpMessageConverter<Object> {

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The ext names. */
    protected String[] extNames;

    /** The allow name param. */
    protected boolean allowNameParam;

    private boolean allowNameAsLastUri;

    /** The name param key. */
    protected String nameParamKey = "fileName";

    /** The resolver path. */
    protected String resolverPath = "data";

    /** The allow resolver path. */
    protected boolean allowResolverPath;

    /** The resolver path key. */
    protected String resolverPathKey = "resolverPath";

    /** The allow ignore pagination. */
    protected boolean allowIgnorePagination;

    /** The ignore pagination key. */
    protected String ignorePaginationKey = "ignorePagination";

    /** The template path key. */
    protected String templatePathKey = "template";

    /** The template base path. */
    protected String templateBasePath = "";

    private ClassLoader classLoader;

    /**
     * Construct an {@code AbstractGenericHttpMessageConverter} with no supported media types.
     *
     * @see #setSupportedMediaTypes
     */
    protected AttachHttpMessageConverter() {
        this(null);
    }

    /**
     * Construct an {@code AbstractGenericHttpMessageConverter} with no supported media types.
     *
     * @param classLoader the class loader
     * @see #setSupportedMediaTypes #setSupportedMediaTypes
     */
    protected AttachHttpMessageConverter(ClassLoader classLoader) {
        this.classLoader = classLoader;
        if (this.classLoader == null) {
            this.classLoader = Thread.currentThread().getContextClassLoader();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
        throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
        throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException();
    }

    /**
     * Match ext name.
     *
     * @param name the name
     * @return true, if successful
     */
    protected boolean matchExtName(String name) {
        for (String extName : extNames) {
            if (name.endsWith(extName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * 获取模板
     * </p>
     * .
     *
     * @param request HttpServletRequest
     * @return 模板
     */
    protected InputStream getTemplate(HttpServletRequest request) {
        String templatePath = null;
        // 最高优先级，从request.parameter中获取
        if (Lang.isNotEmpty(templatePathKey)) {
            templatePath = request.getParameter(templatePathKey);

            // 次优先级，从request.attribute中获取
            if (Lang.isEmpty(templatePath)) {
                Object p = request.getAttribute(templatePathKey);
                if (p != null) {
                    templatePath = p.toString();
                }
            }
        }
        // 最后，从请求路径获取
        if (Lang.isEmpty(templatePath)) {
            templatePath = ServletUtils.getRequestURI(request);
        }
        templatePath = UriUtils.linkUri(templateBasePath, templatePath);
        boolean match = matchExtName(templatePath);
        if (!match) {
            templatePath = templatePath + "." + extNames[0];
        }
        logger.debug("TemplatePath -> {}", templatePath);
        InputStream is = classLoader.getResourceAsStream(templatePath);
        if (is == null) {
            String fileName = StringUtils.substringAfterLast(templatePath, "/");
            logger.debug("未找到路径{}对应的模板，使用{}再查找", templatePath, fileName);
            is = classLoader.getResourceAsStream(UriUtils.linkUri(templateBasePath, fileName));
            if (Lang.isEmpty(is)) {
                throw new WebException("未找到[" + fileName + "]对应的模板");
            }
        }
        return is;
    }

    /**
     * Gets the file name.
     *
     * @param request the request
     * @return the file name
     */
    protected String getFileName(HttpServletRequest request) {
        if (request == null) {
            return System.currentTimeMillis() + "." + extNames[0];
        }

        String fileName = null;

        if (allowNameParam) {
            fileName = request.getParameter(nameParamKey);
        }

        if (allowNameAsLastUri && StringUtils.isBlank(fileName)) {
            String uri = ServletUtils.getRequestURI(request);
            fileName = StringUtils.substringAfterLast(uri, "/");
        }

        if (StringUtils.isBlank(fileName)) {
            fileName = System.currentTimeMillis() + "";
        }

        boolean match = matchExtName(fileName);
        if (!match) {
            fileName = fileName + "." + extNames[0];
        }

        return fileName;
    }

    /**
     * <p>
     * 从结果对象获取数据对象
     * </p>
     * .
     *
     * @param result 结果对象
     * @param request HttpServletRequest
     * @return 数据对象
     */
    protected Object getDataFromResult(Object result, HttpServletRequest request) {
        String rp = getResolverPath(request);
        if (result != null) {
            if (Strings.isNotBlank(rp)) {
                return BeanUtils.getProperty(result, rp);
            } else {
                return result;
            }
        }
        return null;
    }

    /**
     * Gets the resolver path.
     *
     * @param request the request
     * @return the resolver path
     */
    protected String getResolverPath(HttpServletRequest request) {
        if (request == null) {
            return resolverPath;
        }
        String rp = null;
        if (allowResolverPath) {
            rp = request.getParameter(resolverPathKey);
        }
        if (StringUtils.isBlank(rp)) {
            rp = resolverPath;
        }
        logger.debug("ResolverPath -> {}", rp);
        return rp;
    }

    /**
     * Gets the default content type.
     *
     * @return the default content type
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected MediaType getDefaultContentType() throws IOException {
        List<MediaType> mediaTypes = getSupportedMediaTypes();
        return !mediaTypes.isEmpty() ? mediaTypes.get(0) : null;
    }

    /**
     * 返回allowNameParam.
     *
     * @return allowNameParam
     */
    public boolean isAllowNameParam() {
        return allowNameParam;
    }

    /**
     * 设置allowNameParam.
     *
     * @param allowNameParam allowNameParam
     */
    public void setAllowNameParam(boolean allowNameParam) {
        this.allowNameParam = allowNameParam;
    }

    /**
     * 返回nameParamKey.
     *
     * @return nameParamKey
     */
    public String getNameParamKey() {
        return nameParamKey;
    }

    /**
     * 设置nameParamKey.
     *
     * @param nameParamKey nameParamKey
     */
    public void setNameParamKey(String nameParamKey) {
        this.nameParamKey = nameParamKey;
    }

    /**
     * 返回extNames.
     *
     * @return extNames
     */
    public String[] getExtNames() {
        return extNames;
    }

    /**
     * 设置extNames.
     *
     * @param extNames extNames
     */
    public void setExtNames(String[] extNames) {
        this.extNames = extNames;
    }

    /**
     * 返回resolverPath.
     *
     * @return resolverPath
     */
    public String getResolverPath() {
        return resolverPath;
    }

    /**
     * 设置resolverPath.
     *
     * @param resolverPath resolverPath
     */
    public void setResolverPath(String resolverPath) {
        this.resolverPath = resolverPath;
    }

    /**
     * 返回allowResolverPath.
     *
     * @return allowResolverPath
     */
    public boolean isAllowResolverPath() {
        return allowResolverPath;
    }

    /**
     * 设置allowResolverPath.
     *
     * @param allowResolverPath allowResolverPath
     */
    public void setAllowResolverPath(boolean allowResolverPath) {
        this.allowResolverPath = allowResolverPath;
    }

    /**
     * 返回resolverPathKey.
     *
     * @return resolverPathKey
     */
    public String getResolverPathKey() {
        return resolverPathKey;
    }

    /**
     * 设置resolverPathKey.
     *
     * @param resolverPathKey resolverPathKey
     */
    public void setResolverPathKey(String resolverPathKey) {
        this.resolverPathKey = resolverPathKey;
    }

    /**
     * 返回allowNameAsLastUri.
     *
     * @return allowNameAsLastUri
     */
    public boolean isAllowNameAsLastUri() {
        return allowNameAsLastUri;
    }

    /**
     * 设置allowNameAsLastUri.
     *
     * @param allowNameAsLastUri allowNameAsLastUri
     */
    public void setAllowNameAsLastUri(boolean allowNameAsLastUri) {
        this.allowNameAsLastUri = allowNameAsLastUri;
    }

    /**
     * 返回allowIgnorePagination.
     *
     * @return allowIgnorePagination
     */
    public boolean isAllowIgnorePagination() {
        return allowIgnorePagination;
    }

    /**
     * 设置allowIgnorePagination.
     *
     * @param allowIgnorePagination allowIgnorePagination
     */
    public void setAllowIgnorePagination(boolean allowIgnorePagination) {
        this.allowIgnorePagination = allowIgnorePagination;
    }

    /**
     * 返回ignorePaginationKey.
     *
     * @return ignorePaginationKey
     */
    public String getIgnorePaginationKey() {
        return ignorePaginationKey;
    }

    /**
     * 设置ignorePaginationKey.
     *
     * @param ignorePaginationKey ignorePaginationKey
     */
    public void setIgnorePaginationKey(String ignorePaginationKey) {
        this.ignorePaginationKey = ignorePaginationKey;
    }

    /**
     * 返回templatePathKey.
     *
     * @return templatePathKey
     */
    public String getTemplatePathKey() {
        return templatePathKey;
    }

    /**
     * 设置templatePathKey.
     *
     * @param templatePathKey templatePathKey
     */
    public void setTemplatePathKey(String templatePathKey) {
        this.templatePathKey = templatePathKey;
    }

    /**
     * 返回templateBasePath.
     *
     * @return templateBasePath
     */
    public String getTemplateBasePath() {
        return templateBasePath;
    }

    /**
     * 设置templateBasePath.
     *
     * @param templateBasePath templateBasePath
     */
    public void setTemplateBasePath(String templateBasePath) {
        this.templateBasePath = templateBasePath;
    }

    /**
     * Gets the class loader.
     *
     * @return the class loader
     */
    public ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * Sets the class loader.
     *
     * @param classLoader the new class loader
     */
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
