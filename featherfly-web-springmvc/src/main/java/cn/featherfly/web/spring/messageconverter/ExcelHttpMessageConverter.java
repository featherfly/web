package cn.featherfly.web.spring.messageconverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;

import cn.featherfly.data.office.excel.ExcelDataMapper;
import cn.featherfly.data.office.excel.ExcelDataSource;
import cn.featherfly.data.office.excel.ExcelSwaggerModelMapper;
import cn.featherfly.web.spring.interceptor.RequestHolderInterceptor;
import jakarta.servlet.http.HttpServletRequest;

/**
 * ExcelHttpMessageConverter.
 *
 * @author zhongj
 */
public class ExcelHttpMessageConverter extends AttachHttpMessageConverter {

    private ExcelDataMapper<?> mapper = new ExcelSwaggerModelMapper<>();

    /**
     * Instantiates a new Excel http message converter.
     */
    public ExcelHttpMessageConverter() {
        this(null);
    }

    /**
     * Instantiates a new Excel http message converter.
     *
     * @param classLoader the class loader
     */
    public ExcelHttpMessageConverter(ClassLoader classLoader) {
        super(classLoader);
        extNames = new String[] { "xlsx", "xls" };
        resolverPath = "data.content";
        setSupportedMediaTypes(
            Arrays.asList(new MediaType("application", "excel"), new MediaType("application", "*+excel")));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void writeInternal(Object result, Type type, HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
        HttpServletRequest request = RequestHolderInterceptor.getHttpServletRequest();

        String fileName = getFileName(request);
        fileName = new String(fileName.getBytes(), "ISO-8859-1"); // 各浏览器基本都支持ISO编码
        outputMessage.getHeaders().set("Content-Disposition", "attachment;filename=" + fileName);
        outputMessage.getHeaders().setContentType(getDefaultContentType());
        // outputMessage.getHeaders().setContentLength();

        Object data = getDataFromResult(result, request);
        if (data != null) {
            Collection<Object> datas;
            if (data instanceof Collection) {
                datas = (Collection<Object>) data;
            } else {
                datas = new ArrayList<>();
                datas.add(data);
            }
            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                @SuppressWarnings("rawtypes")
                ExcelDataSource source = new ExcelDataSource(workbook, mapper);
                source.addDataSet().addRecords(datas);
                source.save(outputMessage.getBody());
            }
        }
    }

    /**
     * 返回mapper
     *
     * @return mapper mapper
     */
    public ExcelDataMapper<?> getMapper() {
        return mapper;
    }

    /**
     * 设置mapper
     *
     * @param mapper mapper
     */
    public void setMapper(ExcelDataMapper<?> mapper) {
        this.mapper = mapper;
    }
}
