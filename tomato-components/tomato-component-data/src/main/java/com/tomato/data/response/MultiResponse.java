package com.tomato.data.response;

import java.util.Collections;
import java.util.List;

/**
 * 多数据返回
 *
 * @author lizhifu
 * @date 2022/5/26
 */
public class MultiResponse<T> extends Response {
    private static final long serialVersionUID = 1L;
    /**
     * 数据
     */
    private List<T> data;

    public List<T> getData() {
        if (null == data) {
            return Collections.emptyList();
        }
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MultiResponse{" +
                "data=" + data +
                '}';
    }

    public static MultiResponse buildSuccess() {
        MultiResponse response = new MultiResponse();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        return response;
    }

    public static MultiResponse buildFailure(String errCode, String errMessage) {
        MultiResponse response = new MultiResponse();
        response.setMessage(errMessage);
        response.setCode(errCode);
        return response;
    }

    public static <T> MultiResponse<T> of(List<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }
}
