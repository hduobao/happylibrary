package com.hf.happylibrary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hf.happylibrary.mapper.BookMapper;
import com.hf.happylibrary.model.domain.Book;
import com.hf.happylibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

}




