package com.tomato.data.dto.rep;

/**
 * 返回码接口
 *
 * @author lizhifu
 * @date 2022/5/26
 */
public interface IResponseCode {

    /**
     * 获取返回码
     *
     * @return
     */
    String getCode();

    /**
     * 获取返回信息
     *
     * @return
     */
    String getMessage();
}
