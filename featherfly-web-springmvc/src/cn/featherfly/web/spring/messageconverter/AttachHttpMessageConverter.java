
package cn.featherfly.web.spring.messageconverter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.exception.AssertStandardSys;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.StringUtils;
import cn.featherfly.web.servlet.ServletUtils;

/**
 * <p>
 * JxlsHttpMessageConverter
 * </p>
 * 
 * @author 钟冀
 */
public abstract class AttachHttpMessageConverter extends AbstractGenericHttpMessageConverter<Object> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String[] extNames;

    protected boolean allowNameParam;

    private boolean allowNameAsLastUri;

    protected String nameParamKey = "fileName";

    protected String resolverPath = "data";

    protected boolean allowResolverPath;

    protected String resolverPathKey = "resolverPath";

    /**
     * {@inheritDoc}
     */
    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return null;
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
        return null;
    }

    protected boolean matchExtName(String name) {
        for (String extName : extNames) {
            if (name.endsWith(extName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * <p>
     * 获取模板
     * </p>
     * 
     * @param request
     *            HttpServletRequest
     * @return 模板
     */
    protected InputStream getTemplate(HttpServletRequest request) {
        String templatePath = ServletUtils.getRequestURI(request);
        boolean match = matchExtName(templatePath);
        if (!match) {
            templatePath = templatePath + "." + extNames[0];
        }
        logger.debug("TemplatePath -> {}", templatePath);
        InputStream is = ClassLoaderUtils.getResourceAsStream(templatePath, this.getClass());
        AssertStandardSys.isNotNull(is, "未找到[" + templatePath + "]对应的模板");
        return is;
    }

    protected String getFileName(HttpServletRequest request) {
        if (request == null) {
            return System.currentTimeMillis() + "" + extNames[0];
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
     * 
     * @param result
     *            结果对象
     * @param request
     *            HttpServletRequest
     * @return 数据对象
     */
    protected Object getDataFromResult(Object result, HttpServletRequest request) {
        String rp = getResolverPath(request);
        if (result != null) {
            if (StringUtils.isNotBlank(rp)) {
                return BeanUtils.getProperty(result, rp);
            } else {
                return result;
            }
        }
        return null;
    }

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

    protected MediaType getDefaultContentType() throws IOException {
        List<MediaType> mediaTypes = getSupportedMediaTypes();
        return (!mediaTypes.isEmpty() ? mediaTypes.get(0) : null);
    }

    /**
     * 返回allowNameParam
     * 
     * @return allowNameParam
     */
    public boolean isAllowNameParam() {
        return allowNameParam;
    }

    /**
     * 设置allowNameParam
     * 
     * @param allowNameParam
     *            allowNameParam
     */
    public void setAllowNameParam(boolean allowNameParam) {
        this.allowNameParam = allowNameParam;
    }

    /**
     * 返回nameParamKey
     * 
     * @return nameParamKey
     */
    public String getNameParamKey() {
        return nameParamKey;
    }

    /**
     * 设置nameParamKey
     * 
     * @param nameParamKey
     *            nameParamKey
     */
    public void setNameParamKey(String nameParamKey) {
        this.nameParamKey = nameParamKey;
    }

    /**
     * 返回extNames
     * 
     * @return extNames
     */
    public String[] getExtNames() {
        return extNames;
    }

    /**
     * 设置extNames
     * 
     * @param extNames
     *            extNames
     */
    public void setExtNames(String[] extNames) {
        this.extNames = extNames;
    }

    /**
     * 返回resolverPath
     * 
     * @return resolverPath
     */
    public String getResolverPath() {
        return resolverPath;
    }

    /**
     * 设置resolverPath
     * 
     * @param resolverPath
     *            resolverPath
     */
    public void setResolverPath(String resolverPath) {
        this.resolverPath = resolverPath;
    }

    /**
     * 返回allowResolverPath
     * 
     * @return allowResolverPath
     */
    public boolean isAllowResolverPath() {
        return allowResolverPath;
    }

    /**
     * 设置allowResolverPath
     * 
     * @param allowResolverPath
     *            allowResolverPath
     */
    public void setAllowResolverPath(boolean allowResolverPath) {
        this.allowResolverPath = allowResolverPath;
    }

    /**
     * 返回resolverPathKey
     * 
     * @return resolverPathKey
     */
    public String getResolverPathKey() {
        return resolverPathKey;
    }

    /**
     * 设置resolverPathKey
     * 
     * @param resolverPathKey
     *            resolverPathKey
     */
    public void setResolverPathKey(String resolverPathKey) {
        this.resolverPathKey = resolverPathKey;
    }

    /**
     * 返回allowNameAsLastUri
     * 
     * @return allowNameAsLastUri
     */
    public boolean isAllowNameAsLastUri() {
        return allowNameAsLastUri;
    }

    /**
     * 设置allowNameAsLastUri
     * 
     * @param allowNameAsLastUri
     *            allowNameAsLastUri
     */
    public void setAllowNameAsLastUri(boolean allowNameAsLastUri) {
        this.allowNameAsLastUri = allowNameAsLastUri;
    }

}
