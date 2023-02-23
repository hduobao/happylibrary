package com.hf.happylibrary.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373130793L;

    private Long userAccount;

    private String userPassword;

    private String checkPassword;
}
