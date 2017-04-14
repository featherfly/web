package cn.featherfly.web.spring.messageconverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.lang.reflect.GenericClass;
import cn.featherfly.conversion.core.Conversion;
import cn.featherfly.conversion.core.ConversionPolicysJdk8;
import cn.featherfly.conversion.core.TypeConversion;
import cn.featherfly.web.spring.interceptor.RequestHolderInterceptor;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zj on 2017/4/11.
 */
public class ExcelHttpMessageConverter extends AttachHttpMessageConverter {

	@SuppressWarnings("rawtypes")
	private Conversion conversion = new TypeConversion(ConversionPolicysJdk8.getFormatConversionPolicy());

	/**
	 */
	public ExcelHttpMessageConverter() {
		this.extNames = new String[] { "xlsx", "xls" };
		this.resolverPath = "data.content";
		setSupportedMediaTypes(
				Arrays.asList(new MediaType("application", "excel"), new MediaType("application", "*+excel")));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		HttpServletRequest request = RequestHolderInterceptor.getHttpServletRequest();

		String fileName = getFileName(request);

		String rp = getResolverPath(request);

		fileName = new String(fileName.getBytes(), "ISO-8859-1"); // 各浏览器基本都支持ISO编码
		outputMessage.getHeaders().set("Content-Disposition", "attachment;filename=" + fileName);
		outputMessage.getHeaders().setContentType(getDefaultContentType());
		// outputMessage.getHeaders().setContentLength();

		if (o != null) {
			// Object obj = BeanUtils.getProperty(o, "data");
			// Object datas = BeanUtils.getProperty(obj, "content");
			Object datas = BeanUtils.getProperty(o, rp);
			List<String> titles = new ArrayList<>();
			List<List<Object>> recoreds = new ArrayList<>();
			if (datas instanceof Collection) {
				((Collection<?>) datas).forEach(data -> {
					BeanDescriptor<?> beanDescriptor = BeanDescriptor.getBeanDescriptor(data.getClass());
					if (titles.isEmpty()) {
						for (BeanProperty<?> beanProperty : beanDescriptor.getBeanProperties()) {
							ApiModelProperty apiModelProperty = (ApiModelProperty) beanProperty
									.getAnnotation(ApiModelProperty.class);
							titles.add(apiModelProperty.value());
						}
					}

					List<Object> recored = new ArrayList<>();
					for (BeanProperty<?> beanProperty : beanDescriptor.getBeanProperties()) {
						Object object = beanProperty.getValue(data);
						recored.add(object);
					}
					recoreds.add(recored);
				});
			}
			try (XSSFWorkbook wb = new XSSFWorkbook()) {
				XSSFSheet sheet = wb.createSheet();
				createRow(titles, 0, sheet);
				for (int i = 0; i < recoreds.size(); i++) {
					createRow(recoreds.get(i), i + 1, sheet);
				}
				wb.write(outputMessage.getBody());
			}
		}
	}

	private void createRow(List<?> rowValues, int rowNum, XSSFSheet sheet) {
		XSSFRow row = sheet.createRow(rowNum);
		for (int i = 0; i < rowValues.size(); i++) {
			Object value = rowValues.get(i);
			XSSFCell cell = row.createCell(i);
			setCellValue(value, cell);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setCellValue(Object value, XSSFCell cell) {
		if (value != null) {
			conversion.toString(value, new GenericClass(value.getClass()));
		}
	}

	@SuppressWarnings("rawtypes")
	public Conversion getConversion() {
		return conversion;
	}

	public void setConversion(@SuppressWarnings("rawtypes") Conversion conversion) {
		this.conversion = conversion;
	}
}
