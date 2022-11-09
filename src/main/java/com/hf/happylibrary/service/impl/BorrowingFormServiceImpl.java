package com.hf.happylibrary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hf.happylibrary.common.ErrorCode;
import com.hf.happylibrary.exception.BusinessException;
import com.hf.happylibrary.mapper.BookMapper;
import com.hf.happylibrary.mapper.BorrowingFormMapper;
import com.hf.happylibrary.model.domain.Book;
import com.hf.happylibrary.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hf.happylibrary.model.domain.BorrowingForm;
import com.hf.happylibrary.service.BorrowingFormService;

import java.util.Date;

/**
* @author huanghuiyuan
* @description 针对表【borrowing_form(借阅表)】的数据库操作Service实现
* @createDate 2022-11-08 16:54:45
*/
@Service
public class BorrowingFormServiceImpl extends ServiceImpl<BorrowingFormMapper, BorrowingForm>
    implements BorrowingFormService{

    @Autowired
    private BorrowingFormMapper borrowingFormMapper;

    @Autowired
    private BookMapper bookMapper;

    /**
     * 借阅图书操作
     * @param user
     * @param bookId
     * @return
     */
    @Override
    public boolean borrowBook(User user, Long bookId) {

        if (user.getViolation() >= 3) {
            throw new BusinessException(ErrorCode.STATUS_ERROR,"违约次数过多，无法借书");
        }
        BorrowingForm form = new BorrowingForm();
        form.setUserId(user.getId());
        form.setBookId(bookId);
        form.setBorrowTime(new Date());
        int insert = borrowingFormMapper.insert(form);
        if (insert > 0) {
            LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Book::getId,bookId);
            Book book = bookMapper.selectOne(queryWrapper);
            book.setInLibraryTotal(book.getInLibraryTotal() - 1);
            bookMapper.updateById(book);
        }

        return insert > 0;
    }

    /**
     * 归还图书操作
     * @param user
     * @param borrowId
     * @return
     */
    @Override
    public boolean returnBook(User user, Long borrowId) {

        BorrowingForm form = borrowingFormMapper.selectById(borrowId);
        if (form == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        if (!user.getId().equals(form.getUserId())) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        form.setReturnTime(new Date());
        int result = borrowingFormMapper.updateById(form);
        if (result != 0) {
            BorrowingForm selectBorrow = this.getById(borrowId);
            LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Book::getId,selectBorrow.getBookId());
            Book book = bookMapper.selectOne(queryWrapper);
            book.setInLibraryTotal(book.getInLibraryTotal() - 1);
            bookMapper.updateById(book);
        }
        return result > 0;
    }

}




