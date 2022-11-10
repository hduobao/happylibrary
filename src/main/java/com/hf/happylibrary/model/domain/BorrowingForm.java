package com.hf.happylibrary.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 借阅表
 * @TableName borrowing_form
 */
@TableName(value ="borrowing_form")
@Data
public class BorrowingForm implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 书籍id
     */
    private Long bookId;

    /**
     * 借阅图书的期限
     */
    private Integer borrowTerm;

    /**
     * 借阅时间
     */
    private Date borrowTime;

    /**
     * 归还时间
     */
    private Date returnTime;

    /**
     * 是否违规
     */
    private Integer returnStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}