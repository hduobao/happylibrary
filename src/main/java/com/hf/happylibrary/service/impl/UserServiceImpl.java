package com.hf.happylibrary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hf.happylibrary.common.ErrorCode;
import com.hf.happylibrary.exception.BusinessException;
import com.hf.happylibrary.mapper.UserMapper;
import com.hf.happylibrary.model.domain.User;
import com.hf.happylibrary.model.request.UserRegisterRequest;
import com.hf.happylibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author huanghuiyuan
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2022-11-08 12:36:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        Long userAccount = user.getUserAccount();
        String userPassword = user.getUserPassword();
        if (userAccount == null || userPassword == null) {
            throw new BusinessException(ErrorCode.LOGIN_PARAMS_NULL);
        }
        //先对用户的密码进行加密处理
        String encryptPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserAccount,user.getUserAccount());
        //根据用户的学号查询数据库
        User selectUser = userMapper.selectOne(queryWrapper);
        if (selectUser == null) {
            throw new BusinessException(ErrorCode.LOGIN_ERROR);
        }
        //如果查询出的用户密码和加密后的参数密码不一致
        if (!selectUser.getUserPassword().equals(encryptPassword)) {
            throw new BusinessException(ErrorCode.LOGIN_ERROR);
        }
        //如果用户状态异常
        if (selectUser.getUserStatus() != 1) {
            throw new BusinessException(ErrorCode.STATUS_ERROR);
        }

        return selectUser;
    }

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    @Override
    public boolean userRegister(UserRegisterRequest userRegisterRequest) {

        Long userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (userAccount == null || userPassword == null || checkPassword == null) {
            throw new BusinessException(ErrorCode.REGISTER_PARAMS_NULL);
        }
        if (userAccount.toString().length() < 5 ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号过短");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次输入的密码不一致");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount, userAccount);
        User selectUser = userMapper.selectOne(queryWrapper);
        if (selectUser != null) {
            throw new BusinessException(ErrorCode.EXIST_ERROR);
        }
        //如果以上的判断都可以通过，则可以真正的插入数据
        //加密
        String encryptPassword = DigestUtils.md5DigestAsHex((userPassword).getBytes());
        //插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);

        return saveResult;
    }

    public static boolean isAdmin(User user) {
        boolean flag = false;
        if (user.getUserRole() == 1) {
            flag = true;
        }

        return flag;
    }

}




