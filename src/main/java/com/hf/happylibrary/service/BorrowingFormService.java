package com.hf.happylibrary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hf.happylibrary.model.domain.BorrowingForm;
import com.hf.happylibrary.model.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asus
* @description 针对表【borrowing_form(借阅表)】的数据库操作Service
* @createDate 2022-11-08 16:54:45
*/
public interface BorrowingFormService extends IService<BorrowingForm> {

    boolean borrowBook(User user, Long bookId);

    boolean returnBook(User user, Long borrowId);
}
