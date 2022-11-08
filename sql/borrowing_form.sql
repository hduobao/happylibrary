CREATE TABLE borrowing_form(
                               id           BIGINT AUTO_INCREMENT COMMENT 'id'
                                   PRIMARY KEY,
                               user_id BIGINT NOT NULL COMMENT '用户id',
                               book_id BIGINT NOT NULL COMMENT '书籍id',
                               borrow_time   DATETIME NULL COMMENT '借阅时间',
                               return_time   DATETIME COMMENT '归还时间',
                               create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
                               update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               is_delete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) COMMENT '借阅表'