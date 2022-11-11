-- auto-generated definition
create table borrowing_form
(
    id            bigint auto_increment comment 'id'
        primary key,
    user_id       bigint                             not null comment '用户id',
    book_id       bigint                             not null comment '书籍id',
    borrow_term   int      default 30                not null comment '借阅图书的期限',
    borrow_time   datetime                           null comment '借阅时间',
    return_time   datetime                           null comment '归还时间',
    return_status tinyint  default 0                 not null comment '是否违规',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0                 not null comment '是否删除'
)
    comment '借阅表';

