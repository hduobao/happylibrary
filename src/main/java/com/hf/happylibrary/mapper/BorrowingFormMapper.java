package com.hf.happylibrary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hf.happylibrary.model.domain.BorrowingForm;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asus
* @description 针对表【borrowing_form(借阅表)】的数据库操作Mapper
* @createDate 2022-11-08 16:54:45
* @Entity generator.domain.BorrowingForm
*/
@Mapper
public interface BorrowingFormMapper extends BaseMapper<BorrowingForm> {

}




