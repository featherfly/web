
package cn.featherfly.web.spring.messageconverter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import net.sf.jxls.parser.Cell;
import net.sf.jxls.processor.CellProcessor;
import net.sf.jxls.processor.RowProcessor;
import net.sf.jxls.transformer.Row;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import cn.featherfly.common.exception.AssertStandardSys;
import cn.featherfly.common.exception.StandardSysException;


/**
 * <p>
 * ExcelHttpMessageConverter
 * </p>
 * TODO 暂时先这样吧，需要使用excel直接使用JxlsView就行了
 * @author 钟冀
 */
public class JxlsHttpMessageConverter extends AbstractGenericHttpMessageConverter<Object>{
    
    private boolean autoHeight = true;
    
    private String extName = "xlsx";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object read(Type type, Class<?> contextClass,
            HttpInputMessage inputMessage) throws IOException,
            HttpMessageNotReadableException {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeInternal(Object t, Type type,
            HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {
//        String name = null;
//        if (StringUtils.isNotBlank(fileName)) {
//            name = fileName;
//        } else {
//            name = StringUtils.substringAfterLast(finalLocation, "/");
//        }
//        String filePrefix = StringUtils.substringAfterLast(name, ".");
//        if (!name.endsWith(filePrefix)) {
//            name = name + filePrefix;
//        }
//        if (StringUtils.isBlank(name)) {
//            throw new StandardSysException("下载文件的文件未指定下载文件名");
//        }
//        response.setContentType(EXCEL_CONTENT_TYPE);
//        response.setHeader("Content-Type", EXCEL_CONTENT_TYPE);
//        if (request.getHeader("User-Agent").indexOf("MSIE 5.5") != -1) {
//            response.setHeader("Content-Disposition", "filename="
//                    + new String(name.getBytes(), fileNameCharset));
//        } else {
//            response.setHeader("Content-disposition",
//                    "attachment;filename="
//                            + new String(name.getBytes(), fileNameCharset));
//        }
        InputStream in = null;
        Workbook workbook;
        try {
            in = getTemplate(t, type);
            XLSTransformer transformer = new XLSTransformer();
            Map<?, ?> beans = null;
            if (t == null) {
                beans = new HashMap<>();
            } else {
                if (t instanceof Map) {
                    beans = (Map<?, ?>) t;
                } else {
                    beans = PropertyUtils.describe(t);
                }
            }
            if (autoHeight) {
                transformer.registerCellProcessor(new CellProcessor() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public void processCell(Cell cell, Map namedCells) {
                        CellStyle cellStyle = cell.getPoiCell().getCellStyle();
                        cellStyle.setWrapText(true);
                    }
                });
                transformer.registerRowProcessor(new RowProcessor() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public void processRow(Row row, Map namedCells) {
                        short h = -1;
                        row.getPoiRow().setHeight(h);
                    }
                });
            }
            workbook = transformer.transformXLS(in, beans);
            workbook.write(outputMessage.getBody());
            outputMessage.getBody().flush();
        } catch (Exception e) {
            throw new StandardSysException(e);
        } finally {
            if (in != null) {
                IOUtils.closeQuietly(in);
            }
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        // FIXME 这里先都通过
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object readInternal(Class<? extends Object> clazz,
            HttpInputMessage inputMessage) throws IOException,
            HttpMessageNotReadableException {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }
    
    /**
     * <p>
     * 获取模板
     * </p>
     * @param value value
     * @param type type
     * @return 模板
     */
    protected InputStream getTemplate(Object value, Type type) {
        if (value != null) {
            return getTemplate(value.getClass());
        } else if (type instanceof Class) {
            return getTemplate((Class<?>) type);
        }
        throw new StandardSysException("找不到模板文件");
    }
        
    private InputStream getTemplate(Class<?> vt) {
        String name = vt.getSimpleName() + "." + extName;
        InputStream is = vt.getResourceAsStream(name);
        if (is == null) {
            is = vt.getResourceAsStream("/" + name);
        }
        AssertStandardSys.isNotNull(is, "未找到[" + vt.getName() + "]对应的模板");
        return  is;
    }

    /**
     * 返回autoHeight
     * @return autoHeight
     */
    public boolean isAutoHeight() {
        return autoHeight;
    }

    /**
     * 设置autoHeight
     * @param autoHeight autoHeight
     */
    public void setAutoHeight(boolean autoHeight) {
        this.autoHeight = autoHeight;
    }

    /**
     * 返回extName
     * @return extName
     */
    public String getExtName() {
        return extName;
    }

    /**
     * 设置extName
     * @param extName extName
     */
    public void setExtName(String extName) {
        this.extName = extName;
    }
}
