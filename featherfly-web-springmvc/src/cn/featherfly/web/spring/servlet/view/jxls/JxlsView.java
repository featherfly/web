
package cn.featherfly.web.spring.servlet.view.jxls;

import cn.featherfly.common.exception.AssertStandardSys;
import cn.featherfly.common.exception.StandardSysException;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.StringUtils;
import cn.featherfly.web.servlet.ServletUtils;
import net.sf.jxls.parser.Cell;
import net.sf.jxls.processor.CellProcessor;
import net.sf.jxls.processor.RowProcessor;
import net.sf.jxls.transformer.Row;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>
 * JxlsView
 * </p>
 * 
 * @author 钟冀
 */
public class JxlsView extends AbstractXlsxView{
    
    private boolean autoHeight = true;
    
    private String suffix = "xlsx";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String path = ServletUtils.getRequestURI(request);
        if (path.indexOf('.') < 0) {
            path = path + "." + suffix;
        }
        String name = StringUtils.substringAfterLast(path, "/");        
        InputStream in = null;
        try {
            in = getTemplate(path, name, request.getServletContext());
            XLSTransformer transformer = new XLSTransformer();
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
            workbook = transformer.transformXLS(in, model);
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new StandardSysException(e);
        } finally {
            if (in != null) {
                IOUtils.closeQuietly(in);
            }
        }
    }
    
    private InputStream getTemplate(String filePath, String fileName, ServletContext context) {
        InputStream is = ClassLoaderUtils.getResourceAsStream(filePath, context.getClass());
        if (is == null) {
            is = ClassLoaderUtils.getResourceAsStream("/" + fileName, context.getClass());
        }
        AssertStandardSys.isNotNull(is, "未找到[" + filePath + "," + fileName + "]对应的模板");
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
     * 返回suffix
     * @return suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 设置suffix
     * @param suffix suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
