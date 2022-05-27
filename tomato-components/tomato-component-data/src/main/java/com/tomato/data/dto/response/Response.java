package com.tomato.data.dto.response;

import com.tomato.data.dto.DTO;

/**
 * 数据返回结果
 *
 * @author lizhifu
 * @date 2022/5/26
 */
public class Response extends DTO {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    /**
     * 是否成功
     * @param response
     * @return
     */
    public static boolean isSuccess(Response response) {
        return response != null && ResponseCode.SUCCESS.getCode().equals(response.getCode());
    }
    public static Response buildSuccess() {
        Response response = new Response();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        return response;
    }

    public static Response buildFailure(String code, String message) {
        Response response = new Response();
        response.setMessage(message);
        response.setCode(code);
        return response;
    }
}
