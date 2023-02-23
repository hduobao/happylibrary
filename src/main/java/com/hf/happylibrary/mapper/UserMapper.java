package com.hf.happylibrary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hf.happylibrary.model.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asus
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2022-11-08 12:36:25
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




