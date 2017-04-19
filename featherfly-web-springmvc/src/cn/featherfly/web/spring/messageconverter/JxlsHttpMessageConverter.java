
package cn.featherfly.web.spring.messageconverter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;

import cn.featherfly.web.spring.interceptor.RequestHolderInterceptor;

/**
 * <p>
 * JxlsHttpMessageConverter
 * </p>
 * 
 * @author 钟冀
 */
public class JxlsHttpMessageConverter extends AttachHttpMessageConverter {

    private boolean autoHeight = true;

    /**
     * 
     */
    public JxlsHttpMessageConverter() {
        super();
        extNames = new String[] { "xlsx", "xls" };
        setSupportedMediaTypes(
                Arrays.asList(new MediaType("application", "excel"), new MediaType("application", "*+excel")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeInternal(Object result, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        HttpServletRequest request = RequestHolderInterceptor.getHttpServletRequest();
        String fileName = getFileName(request);
        fileName = new String(fileName.getBytes(), "ISO-8859-1"); // 各浏览器基本都支持ISO编码
        Object source = getDataFromResult(result, request);
        outputMessage.getHeaders().set("Content-Disposition", "attachment;filename=" + fileName);
        outputMessage.getHeaders().setContentType(getDefaultContentType());
        InputStream is = getTemplate(request);
        Context context = new Context();
        context.putVar(resolverPath, source);
        JxlsHelper.getInstance().processTemplate(is, outputMessage.getBody(), context);
        // Transformer transformer = TransformerFactory.createTransformer(is,
        // outputMessage.getBody());
        // transformer.write();
    }

    /**
     * 返回autoHeight
     * 
     * @return autoHeight
     */
    public boolean isAutoHeight() {
        return autoHeight;
    }

    /**
     * 设置autoHeight
     * 
     * @param autoHeight
     *            autoHeight
     */
    public void setAutoHeight(boolean autoHeight) {
        this.autoHeight = autoHeight;
    }
}
