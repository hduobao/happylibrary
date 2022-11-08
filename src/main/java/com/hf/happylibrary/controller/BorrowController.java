package com.hf.happylibrary.controller;

import com.hf.happylibrary.common.BaseResponse;
import com.hf.happylibrary.common.ErrorCode;
import com.hf.happylibrary.exception.BusinessException;
import com.hf.happylibrary.model.domain.User;
import com.hf.happylibrary.service.BorrowingFormService;
import com.hf.happylibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 用于处理借书以及还书的操作
 */
@RestController
@RequestMapping("/borrowingForm")
public class BorrowController {

    @Autowired
    private UserService userService;

    @Autowired
    private BorrowingFormService borrowingFormService;

    /**
     * 处理借阅图书操作
     * @param bookId
     * @param request
     * @return
     */
    @PostMapping("/borrow")
    public BaseResponse<String> borrow(Long bookId,
                                       HttpServletRequest request) {
        if (bookId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //获取当前登录用户的id
        Long userId = (Long) request.getSession().getAttribute("user");
        if (userId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        User user = userService.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        //把当前登录用户的id以及书籍的id作参数进行操作
        boolean result = borrowingFormService.borrowBook(user, bookId);
        if (!result) {
            return BaseResponse.error("借阅失败");
        }
        return BaseResponse.error("借阅成功");
    }

    @PostMapping("return")
    public BaseResponse<String> returnBook(Long borrowId,
                                           HttpServletRequest request){
        if (borrowId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //获取当前登录用户的id
        Long userId = (Long) request.getSession().getAttribute("user");
        if (userId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        User user = userService.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        boolean result = borrowingFormService.returnBook(user, borrowId);
        if (!result) {
            return BaseResponse.error("归还失败");
        }
        return BaseResponse.success("归还成功");
    }
}
