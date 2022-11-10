package com.hf.happylibrary.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hf.happylibrary.model.domain.Book;
import com.hf.happylibrary.model.domain.User;

import java.util.List;

/**
* @author asus
* @description 针对表【book(图书表)】的数据库操作Service
* @createDate 2022-11-08 12:36:25
*/
public interface BookService extends IService<Book> {

    LambdaQueryWrapper<Book> searchByName(String keyword, LambdaQueryWrapper<Book> queryWrapper);

    LambdaQueryWrapper<Book>  searchByAnyWord(String keyword, LambdaQueryWrapper<Book> queryWrapper);

    LambdaQueryWrapper<Book>  searchByAuthor(String keyword, LambdaQueryWrapper<Book> queryWrapper);

    LambdaQueryWrapper<Book>  searchByCategory(String keyword, LambdaQueryWrapper<Book> queryWrapper);

    LambdaQueryWrapper<Book>  searchByPublisher(String keyword, LambdaQueryWrapper<Book> queryWrapper);

    LambdaQueryWrapper<Book>  searchById(String keyword, LambdaQueryWrapper<Book> queryWrapper);
}
