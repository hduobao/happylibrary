package com.hf.happylibrary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hf.happylibrary.model.domain.User;
import com.hf.happylibrary.model.request.UserRegisterRequest;

/**
* @author asus
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2022-11-08 12:36:25
*/
public interface UserService extends IService<User> {

    User userLogin(User user);

    boolean userRegister(UserRegisterRequest userRegisterRequest);
}
