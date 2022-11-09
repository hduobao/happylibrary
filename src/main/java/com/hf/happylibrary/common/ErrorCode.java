package com.hf.happylibrary.common;

/**
 * 错误码
 */
public enum ErrorCode {

    SUCCESS(1,"ok","登陆成功"),
    LOGIN_ERROR(10001,"账号或密码错误","输入的用户名或密码有误"),
    LOGIN_PARAMS_NULL(10002,"账号或密码为空","登录时必须输入账号和密码"),
    REGISTER_PARAMS_NULL(10101,"账号或密码或校验密码为空","注册时必须输入账号密码以及校验密码"),
    STATUS_ERROR(20002,"用户状态异常",""),
    EXIST_ERROR(20003,"用户已存在",""),
    PARAMS_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"请求数据为空",""),
    NOT_LOGIN(40100,"未登录",""),
    NO_AUTH(40101,"无权限",""),
    SYSTEM_ERROR(50000,"系统内部异常", "");

    private final int code;
    /**
     * 状态码信息
     */
    private final String message;
    /**
     * 状态码描述，详情
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
