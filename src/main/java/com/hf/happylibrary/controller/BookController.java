package com.hf.happylibrary.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hf.happylibrary.common.BaseResponse;
import com.hf.happylibrary.common.ErrorCode;
import com.hf.happylibrary.exception.BusinessException;
import com.hf.happylibrary.model.domain.Book;
import com.hf.happylibrary.service.BookService;
import com.hf.happylibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用于处理book资源的请求
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    /**
     * 增加图书操作
     * @param book
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<String> add(@RequestBody Book book) {
        //判断参数book是否为空
        if (book == null) {
            return BaseResponse.error("传入的book为空");
        }
        //非空则进行插入操作
        boolean result = bookService.save(book);
        if (!result) {
            return BaseResponse.error("添加书籍失败");
        }

        return BaseResponse.success("添加书籍成功");
    }

    @GetMapping("/page")
    public BaseResponse<Page> page(int page, int pageSize) {
        //创建分页构造器
        Page<Book> pageInfo = new Page<>(page, pageSize);
        //创建条件构造器
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        //按照书本的id升序排序
        queryWrapper.orderByAsc(Book::getId);
        Page<Book> result = bookService.page(pageInfo, queryWrapper);
        if (result == null) {
            return BaseResponse.error("暂无数据");
        }
        return BaseResponse.success(pageInfo);
    }

    @PutMapping("/update")
    public BaseResponse<String> update(@RequestBody Book book) {

        if (book == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = bookService.updateById(book);
        if (!result) {
            return BaseResponse.error("修改失败");
        }

        return BaseResponse.success("修改成功");
    }

    @DeleteMapping("delete")
    public BaseResponse<String> delete(Long id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = bookService.removeById(id);
        if (!result) {
            return BaseResponse.error("删除失败");
        }

        return BaseResponse.success("删除成功");
    }


}
