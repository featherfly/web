
package cn.featherfly.web.pagination;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.NumberUtils;
import cn.featherfly.common.structure.page.Pagination;
import cn.featherfly.common.structure.page.SimplePagination;

/**
 * <p>
 * SimplePaginationFactory
 * </p>
 *
 * @author 钟冀
 */
public class RequestParameterPaginationFactory implements PaginationFactory {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * {@inheritDoc}
     */
    @Override
    public Pagination create(HttpServletRequest request) {
        Object p = request.getParameter(pageNumberName);

        Integer pageNumber = NumberUtils.parse(p + "", defaultPageNumber);

        SimplePagination pagination = new SimplePagination();

        pagination.setPageNumber(pageNumber);

        pagination.setPageSize(getPageSize(request));

        return pagination;
    }

    private Integer getPageSize(HttpServletRequest request) {
        if (allowDaynmicPageSize) {
            Object ps = request.getParameter(pageSizeName);
            Integer pageSize = NumberUtils.parse(ps + "", defaultPageSize);
            if (pageSize > allowMaxPageSize) {
                logger.debug("pageSize({}) > allowMaxPageSize({}), set pageSize = allowMaxPageSize");
                pageSize = allowMaxPageSize;
            }
            return pageSize;
        }
        return defaultPageSize;
    }

    //	private Integer getPageNumberSize(HttpServletRequest request) {
    //		if (allowDaynmicPageNumberSize) {
    //			Object ps = request.getParameter(pageNumberName);
    //			return StringUtils.parse(ps + "", defaultPageNumberSize);
    //		}
    //		return defaultPageNumberSize;
    //	}

    // ********************************************************************
    //	property
    // ********************************************************************

    private boolean allowDaynmicPageSize;

    //	private boolean allowDaynmicPageNumberSize;

    // 每页显示数量
    private String pageSizeName = "_ps";

    // 第几页
    private String pageNumberName = "_p";

    // 显示分页页数的个数
    //	private String pageNumberSizeName = "_pns";

    // 默认每页显示数量
    private Integer defaultPageSize = 10;

    // 默认显示分页页数的个数
    private Integer defaultPageNumberSize = 10;

    // 默认显示的页
    private Integer defaultPageNumber = 1;

    // 允许的每页显示最大数
    private Integer allowMaxPageSize = 10;

    /**
     * 返回pageSizeName
     * 
     * @return pageSizeName
     */
    public String getPageSizeName() {
        return pageSizeName;
    }

    /**
     * 设置pageSizeName
     * 
     * @param pageSizeName pageSizeName
     */
    public void setPageSizeName(String pageSizeName) {
        AssertIllegalArgument.isNotEmpty(pageSizeName, "pageSizeName不能为空");
        this.pageSizeName = pageSizeName;
    }

    /**
     * 返回pageNumberName
     * 
     * @return pageNumberName
     */
    public String getPageNumberName() {
        return pageNumberName;
    }

    /**
     * 设置pageNumberName
     * 
     * @param pageNumberName pageNumberName
     */
    public void setPageNumberName(String pageNumberName) {
        AssertIllegalArgument.isNotEmpty(pageNumberName, "pageNumberName不能为空");
        this.pageNumberName = pageNumberName;
    }

    /**
     * 返回defaultPageSize
     * 
     * @return defaultPageSize
     */
    public Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    /**
     * 设置defaultPageSize
     * 
     * @param defaultPageSize defaultPageSize
     */
    public void setDefaultPageSize(Integer defaultPageSize) {
        AssertIllegalArgument.isNotEmpty(defaultPageSize, "defaultPageSize不能为空");
        this.defaultPageSize = defaultPageSize;
    }

    /**
     * 返回defaultPageNumberSize
     * 
     * @return defaultPageNumberSize
     */
    public Integer getDefaultPageNumberSize() {
        return defaultPageNumberSize;
    }

    /**
     * 设置defaultPageNumberSize
     * 
     * @param defaultPageNumberSize defaultPageNumberSize
     */
    public void setDefaultPageNumberSize(Integer defaultPageNumberSize) {
        AssertIllegalArgument.isNotEmpty(defaultPageNumberSize, "defaultPageNumberSize不能为空");
        this.defaultPageNumberSize = defaultPageNumberSize;
    }

    /**
     * 返回defaultPageNumber
     * 
     * @return defaultPageNumber
     */
    public Integer getDefaultPageNumber() {
        return defaultPageNumber;
    }

    /**
     * 设置defaultPageNumber
     * 
     * @param defaultPageNumber defaultPageNumber
     */
    public void setDefaultPageNumber(Integer defaultPageNumber) {
        AssertIllegalArgument.isNotEmpty(defaultPageNumber, "defaultPageNumber不能为空");
        this.defaultPageNumber = defaultPageNumber;
    }

    /**
     * 返回allowDaynmicPageSize
     * 
     * @return allowDaynmicPageSize
     */
    public boolean isAllowDaynmicPageSize() {
        return allowDaynmicPageSize;
    }

    /**
     * 设置allowDaynmicPageSize
     * 
     * @param allowDaynmicPageSize allowDaynmicPageSize
     */
    public void setAllowDaynmicPageSize(boolean allowDaynmicPageSize) {
        this.allowDaynmicPageSize = allowDaynmicPageSize;
    }

    /**
     * 返回allowMaxPageSize
     * 
     * @return allowMaxPageSize
     */
    public Integer getAllowMaxPageSize() {
        return allowMaxPageSize;
    }

    /**
     * 设置allowMaxPageSize
     * 
     * @param allowMaxPageSize allowMaxPageSize
     */
    public void setAllowMaxPageSize(Integer allowMaxPageSize) {
        this.allowMaxPageSize = allowMaxPageSize;
    }
}
