package com.tomato.data.dto.rep;

import java.util.Collections;
import java.util.List;

/**
 * 分页查询返回结果
 *
 * @author lizhifu
 * @date 2022/5/26
 */
public class PageResponse<T> extends Response{
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private int totalCount = 0;
    /**
     * 总页数
     */
    private int pageSize = 1;
    /**
     * 当前页
     */
    private int pageIndex = 1;
    /**
     * 数据
     */
    private List<T> data;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        if (pageSize < 1) {
            return 1;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    public int getPageIndex() {
        if (pageIndex < 1) {
            return 1;
        }
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex < 1) {
            this.pageIndex = 1;
        } else {
            this.pageIndex = pageIndex;
        }
    }

    public List<T> getData() {
        if (null == data) {
            return Collections.emptyList();
        }
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
    public int getTotalPages() {
        return this.totalCount % this.pageSize == 0 ? this.totalCount
                / this.pageSize : (this.totalCount / this.pageSize) + 1;
    }
    @Override
    public String toString() {
        return "PageResponse{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", data=" + data +
                '}';
    }

    public static PageResponse buildSuccess() {
        PageResponse response = new PageResponse();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        return response;
    }

    public static PageResponse buildFailure(String errCode, String errMessage) {
        PageResponse response = new PageResponse();
        response.setMessage(errMessage);
        response.setCode(errCode);
        return response;
    }

    public static <T> PageResponse<T> of(int pageSize, int pageIndex) {
        PageResponse<T> response = new PageResponse<>();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setData(Collections.emptyList());
        response.setTotalCount(0);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }

    public static <T> PageResponse<T> of(List<T> data, int totalCount, int pageSize, int pageIndex) {
        PageResponse<T> response = new PageResponse<>();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setData(data);
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }
}
