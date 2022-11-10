package com.hf.happylibrary.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hf.happylibrary.common.BaseResponse;
import com.hf.happylibrary.common.ErrorCode;
import com.hf.happylibrary.exception.BusinessException;
import com.hf.happylibrary.model.domain.Book;
import com.hf.happylibrary.model.domain.BorrowingForm;
import com.hf.happylibrary.model.domain.User;
import com.hf.happylibrary.service.BookService;
import com.hf.happylibrary.service.BorrowingFormService;
import com.hf.happylibrary.service.UserService;
import com.hf.happylibrary.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private BookService bookService;

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
            return BaseResponse.error("没有传入bookId");
        }
        //获取当前登录用户的id
        Long userId = (Long) request.getSession().getAttribute("user");
        if (userId == null) {
            return BaseResponse.error("用户未登录");
        }
        User user = userService.getById(userId);
        if (user == null) {
            return BaseResponse.error("不存在用户Id为"+ userId + "的用户");
        }
        Book book = bookService.getById(bookId);
        if (book == null) {
            return BaseResponse.error("不存在书号为" + bookId + "的书籍");
        }
        //判断书本的在馆册数是否可借
        if (book.getTotal() <= 0 || book.getInLibraryTotal() <= 0) {
            return BaseResponse.error(book.getBookName() + "的可借阅数量不足");
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
            return BaseResponse.error("传入的borrowId为空");
        }
        //获取当前登录用户的id
        Long userId = (Long) request.getSession().getAttribute("user");
//        if (userId == null) {
//            return BaseResponse.error("用户未登录");
//        }
        User user = userService.getById(1);
        if (user == null) {
            return BaseResponse.error("不存在用户Id为"+ userId + "的用户");
        }
        boolean result = borrowingFormService.returnBook(user, borrowId);
        if (!result) {
            return BaseResponse.error("归还失败");
        }
        return BaseResponse.success("归还成功");
    }

    @GetMapping("/pageByUser")
    public BaseResponse<Page> pageByUser(int page, int pageSize,
                                   HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        if (userId == null) {
            return BaseResponse.error("用户未登录");
        }
        Page<BorrowingForm> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<BorrowingForm> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BorrowingForm::getUserId, userId)
                .orderByAsc(BorrowingForm::getCreateTime);
        Page<BorrowingForm> result = borrowingFormService.page(pageInfo, queryWrapper);
        if (result.getRecords().isEmpty()) {
            return BaseResponse.error("暂无数据");
        }

        return BaseResponse.success(pageInfo);
    }

    @GetMapping("/page")
    public BaseResponse<Page> page(int page, int pageSize,
                                   HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        if (userId == null) {
            return BaseResponse.error("用户未登录");
        }
        User user = userService.getById(userId);
        if (user == null) {
            return BaseResponse.error("找不到用户id为" + userId + "的用户");
        }
        if (!UserServiceImpl.isAdmin(user)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        Page<BorrowingForm> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<BorrowingForm> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BorrowingForm::getUserId, 1)
                .orderByAsc(BorrowingForm::getCreateTime);
        Page<BorrowingForm> result = borrowingFormService.page(pageInfo, queryWrapper);
        if (result.getRecords().isEmpty()) {
            return BaseResponse.error("暂无数据");
        }

        return BaseResponse.success(pageInfo);
    }
}
