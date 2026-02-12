
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-02-12 14:54:12
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.web;

import cn.featherfly.web.pagination.RequestParameterPageFactory;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Test.
 *
 * @author zhongj
 */
public class Test {

    void t() {
        HttpServletRequest req = null;
        RequestParameterPageFactory factory = new RequestParameterPageFactory();
        factory.create(req);
    }
}
