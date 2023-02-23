package com.hf.happylibrary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hf.happylibrary.model.domain.Book;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asus
* @description 针对表【book(图书表)】的数据库操作Mapper
* @createDate 2022-11-08 12:36:25
* @Entity generator.domain.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}




