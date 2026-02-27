
package cn.featherfly.web.spring.servlet.view.jxls;

import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.web.WebException;
import cn.featherfly.web.servlet.ServletUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.jxls.builder.JxlsStreaming;
import org.jxls.common.PoiExceptionLogger;
import org.jxls.common.PoiExceptionThrower;
import org.jxls.logging.JxlsLogger;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.transform.poi.PoiTransformerFactory;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.io.InputStream;
import java.util.Map;

/**
 * JxlsView.
 *
 * @author zhongj
 */
public class JxlsView extends AbstractXlsxView {

    private JxlsLogger jxlsLogger = new PoiExceptionThrower();

    private boolean autoHeight = true;

    private String suffix = "xlsx";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        String path = ServletUtils.getRequestURI(request);
        if (path.indexOf('.') < 0) {
            path = path + "." + suffix;
        }
        String name = StringUtils.substringAfterLast(path, "/");
        try (InputStream is = getTemplate(path, name, request.getServletContext())) {
            JxlsPoiTemplateFillerBuilder.newInstance()
                .withTemplate(is)
                .build()
                .fill(model, response::getOutputStream);

//            String[] names = new String[workbook.getNumberOfSheets()];
//            Lang.each(workbook, (sheet, index) -> names[index] = sheet.getSheetName());
//            Transformer transformer = JxlsPoiTemplateFillerBuilder.newInstance()
//                .getTransformerFactory().create(is,
//                response.getOutputStream(),
//                JxlsStreaming.streamingWithGivenSheets(names), new PoiExceptionLogger());
//            if (autoHeight) {
//                transformer.registerCellProcessor(new CellProcessor() {
//                    @SuppressWarnings("rawtypes")
//                    @Override
//                    public void processCell(Cell cell, Map namedCells) {
//                        CellStyle cellStyle = cell.getCellStyle();
//                        cellStyle.setWrapText(true);
//                    }
//                });
//                transformer.registerRowProcessor(new RowProcessor() {
//                    @SuppressWarnings("rawtypes")
//                    @Override
//                    public void processRow(Row row, Map namedCells) {
//                        short h = -1;
//                        row.getPoiRow().setHeight(h);
//                    }
//                });
//            }
//            transformer.write();
        } catch (Exception e) {
            throw new WebException(e);
        }
    }

    private InputStream getTemplate(String filePath, String fileName, ServletContext context) {
        InputStream is = ClassLoaderUtils.getResourceAsStream(filePath, context.getClass());
        if (is == null) {
            is = ClassLoaderUtils.getResourceAsStream("/" + fileName, context.getClass());
        }
        if (Lang.isEmpty(is)) {
            throw new WebException("未找到[" + filePath + "," + fileName + "]对应的模板");
        }
        return is;
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
     * @param autoHeight autoHeight
     */
    public void setAutoHeight(boolean autoHeight) {
        this.autoHeight = autoHeight;
    }

    /**
     * 返回suffix
     *
     * @return suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 设置suffix
     *
     * @param suffix suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
