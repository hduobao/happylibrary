package com.hf.happylibrary.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hf.happylibrary.common.BaseResponse;
import com.hf.happylibrary.model.domain.User;
import com.hf.happylibrary.model.request.UserRegisterRequest;
import com.hf.happylibrary.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于处理用户资源
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody User user,
                                    HttpServletRequest request) {
        if (user == null) {
            return BaseResponse.error("传入的user为空");
        }
        User result = userService.userLogin(user);
        request.getSession().setAttribute("user",user.getId());
        return BaseResponse.success(result);
    }

    /**
     * 用户退出登录
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return BaseResponse.success("退出成功");
    }

    /**
     * 新用户注册
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return BaseResponse.error("传入的注册参数为空");
        }
        boolean result = userService.userRegister(userRegisterRequest);
        if (!result) {
            return BaseResponse.error("新用户注册失败");
        }
        return BaseResponse.success("新用户注册成功");
    }

    /**
     * 分页查询
     * @param page 页数
     * @param pageSize 每一页的记录条数
     * @param name 模糊查询的条件
     * @return
     */
    @GetMapping("/page")
    public BaseResponse<Page> page(int page, int pageSize, String name) {
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        //添加一个过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),User::getUsername,name);
        //添加排序条件
        queryWrapper.orderByDesc(User::getUpdateTime);
        //执行查询
        userService.page(pageInfo,queryWrapper);

        return BaseResponse.success(pageInfo);
    }

    /**
     * 修改用户信息
     * @param user
     * @param request
     * @return
     */
    @PutMapping("/update")
    public BaseResponse<String> update(@RequestBody User user,
                                       HttpServletRequest request) {

        if (user == null) {
            return BaseResponse.error("传入的user参数为空");
        }
        Long attribute = (Long) request.getSession().getAttribute("user");
        if (attribute == null) {
            return BaseResponse.error("用户未登录");
        }
        boolean result = userService.updateById(user);
        if (!result) {
            return BaseResponse.error("修改用户信息失败");
        }

        return BaseResponse.success("修改用户信息成功");
    }

    /**
     * 用于在修改用户信息时回显数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BaseResponse<User> get(@PathVariable Long id) {
        if (id == null) {
            return BaseResponse.error("传入的id为空");
        }
        User user = userService.getById(id);
        if (user == null) {
            return BaseResponse.error("没有对应用户");
        }

        return BaseResponse.success(user);
    }

    @DeleteMapping("/delete")
    public BaseResponse<String> delete(Long id) {
        if (id == null) {
            return BaseResponse.error("传入的id为空");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, id);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return BaseResponse.error("找不到此用户");
        }
        userService.removeById(id);

        return BaseResponse.success("删除成功");
    }
}
