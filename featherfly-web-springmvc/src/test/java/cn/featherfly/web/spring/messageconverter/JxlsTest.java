
/*
 * All rights Reserved, Designed By zhongj
 * @Title: JxlsTest.java
 * @Package cn.featherfly.web.spring.messageconverter
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-08-30 18:30:30
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.web.spring.messageconverter;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.testng.annotations.Test;

/**
 * JxlsTest.
 *
 * @author zhongj
 */
public class JxlsTest {

    List<Employee> generateSampleEmployeeData() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee e = new Employee();
            e.setName("name_" + i);
            e.setYear(i);
            e.setPayment(BigDecimal.valueOf(i));
            e.setBonus(BigDecimal.valueOf(i));
            e.setBirthDate(new Date());
            list.add(e);
        }
        return list;
    }

    @Test
    public void test() throws Exception {
        List<Employee> employees = generateSampleEmployeeData();
        try (InputStream is = this.getClass().getResourceAsStream("employee.xlsx")) {
            try (OutputStream os = new FileOutputStream("employee_out.xlsx")) {
                Context context = new Context();
                context.putVar("employees", employees);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        }
    }
}
