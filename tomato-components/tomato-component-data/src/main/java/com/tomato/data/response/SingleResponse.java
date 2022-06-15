package com.tomato.data.response;

/**
 * 单条数据返回
 *
 * @author lizhifu
 * @date 2022/5/26
 */
public class SingleResponse<T> extends Response {
    private static final long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SingleResponse{" +
                "data=" + data +
                '}';
    }

    public static SingleResponse buildSuccess() {
        SingleResponse response = new SingleResponse();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        return response;
    }

    public static SingleResponse buildFailure(String errCode, String errMessage) {
        SingleResponse response = new SingleResponse();
        response.setMessage(errMessage);
        response.setCode(errCode);
        return response;
    }
    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }
}
