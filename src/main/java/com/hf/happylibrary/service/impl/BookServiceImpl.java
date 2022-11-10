package com.hf.happylibrary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hf.happylibrary.common.ErrorCode;
import com.hf.happylibrary.exception.BusinessException;
import com.hf.happylibrary.mapper.BookMapper;
import com.hf.happylibrary.model.domain.Book;
import com.hf.happylibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author huanghuiyuan
* @description 针对表【book(图书表)】的数据库操作Service实现
* @createDate 2022-11-08 12:36:25
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public LambdaQueryWrapper<Book> searchByName(String keyword, LambdaQueryWrapper<Book> queryWrapper) {
        if (keyword == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        queryWrapper.like(Book::getBookName, keyword);

        return queryWrapper;
    }

    @Override
    public LambdaQueryWrapper<Book> searchByAnyWord(String keyword, LambdaQueryWrapper<Book> queryWrapper) {
        if (keyword == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        queryWrapper.like(Book::getBookName, keyword)
                .or().like(Book::getAuthor, keyword)
                .or().like(Book::getCategory, keyword)
                .or().like(Book::getPublisher, keyword)
                .or().like(Book::getIntroduction, keyword);

        return queryWrapper;
    }

    @Override
    public LambdaQueryWrapper<Book> searchByAuthor(String keyword, LambdaQueryWrapper<Book> queryWrapper) {
        if (keyword == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        queryWrapper.like(Book::getAuthor, keyword);

        return queryWrapper;
    }

    @Override
    public LambdaQueryWrapper<Book> searchByCategory(String keyword, LambdaQueryWrapper<Book> queryWrapper) {
        if (keyword == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        queryWrapper.like(Book::getCategory, keyword);

        return queryWrapper;
    }

    @Override
    public LambdaQueryWrapper<Book> searchByPublisher(String keyword, LambdaQueryWrapper<Book> queryWrapper) {
        if (keyword == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        queryWrapper.like(Book::getPublisher, keyword);

        return queryWrapper;
    }

    @Override
    public LambdaQueryWrapper<Book> searchById(String keyword, LambdaQueryWrapper<Book> queryWrapper) {
        if (keyword == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        queryWrapper.like(Book::getId, Long.parseLong(keyword));

        return queryWrapper;
    }
}




