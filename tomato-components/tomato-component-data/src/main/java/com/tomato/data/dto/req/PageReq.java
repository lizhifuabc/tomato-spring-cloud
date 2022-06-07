package com.tomato.data.dto.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页查询请求
 *
 * @author lizhifu
 * @date 2022/5/26
 */
public abstract class PageReq extends Req {
    private static final long serialVersionUID = 1L;
    /**
     * 升序
     */
    public static final String ASC = "ASC";
    /**
     * 降序
     */
    public static final String DESC = "DESC";
    /**
     * 默认每页数量
     */
    private static final int DEFAULT_PAGE_SIZE = 10;
    /**
     * 默认每页显示数量
     */
    @ApiModelProperty(value = "每页条数，最大值为 100", required = true, example = "10")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    @Max(value = 100, message = "页码最大值为 100")
    private int pageSize = DEFAULT_PAGE_SIZE;
    /**
     * 当前页码
     */
    @ApiModelProperty(value = "页码，从 1 开始", required = true,example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private int pageIndex = 1;
    /**
     * 排序字段
     */
    private String orderBy;
    /**
     * 排序方式
     */
    private String orderDirection = DESC;
    /**
     * 是否查询总数量
     */
    private boolean needTotalCount = false;

    public int getPageIndex() {
        if (pageIndex < 1) {
            return 1;
        }
        return pageIndex;
    }

    public PageReq setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public int getPageSize() {
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public PageReq setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
        return this;
    }

    public int getOffset() {
        return (getPageIndex() - 1) * getPageSize();
    }

    public String getOrderBy() {
        return orderBy;
    }

    public PageReq setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public PageReq setOrderDirection(String orderDirection) {
        if (ASC.equalsIgnoreCase(orderDirection) || DESC.equalsIgnoreCase(orderDirection)) {
            this.orderDirection = orderDirection;
        }
        return this;
    }
    public boolean isNeedTotalCount() {
        return needTotalCount;
    }

    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }
}
