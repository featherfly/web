
package cn.featherfly.web.pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.common.structure.page.Page;

/**
 * RequestParameterMulitiPageFactory.
 *
 * @author zhongj
 */
public class RequestParameterMulitiPageFactory implements MulitiPageFactory {

    private List<PageFactory> pageFactorys = new ArrayList<>();

    /**
     * Instantiates a new request parameter muliti page factory.
     */
    public RequestParameterMulitiPageFactory() {
    }

    /**
     * Instantiates a new request parameter muliti page factory.
     *
     * @param pageFactorys the page factorys
     */
    public RequestParameterMulitiPageFactory(List<PageFactory> pageFactorys) {
        super();
        setPageFactorys(pageFactorys);
    }

    /**
     * set pageFactorys value.
     *
     * @param pageFactorys pageFactorys
     */
    public void setPageFactorys(List<PageFactory> pageFactorys) {
        this.pageFactorys.clear();
        this.pageFactorys.addAll(pageFactorys);
    }

    /**
     * get pageFactorys value.
     *
     * @return pageFactorys
     */
    public List<PageFactory> getPageFactorys() {
        return new ArrayList<>(pageFactorys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Page> create(HttpServletRequest request) {
        return pageFactorys.stream().map(f -> f.create(request)).collect(Collectors.toList());
    }

}
