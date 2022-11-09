package com.hf.happylibrary.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端返回的数据最终会封装成此对象
 * @param <T>
 */
@Data
public class BaseResponse<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> BaseResponse<T> success(T object) {
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.data = object;
        baseResponse.code = 1;
        return baseResponse;
    }

    public static <T> BaseResponse<T> error(String msg) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.msg = msg;
        baseResponse.code = 0;
        return baseResponse;
    }
}
