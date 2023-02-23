-- auto-generated definition
create table book
(
    id               bigint auto_increment comment 'id'
        primary key,
    book_name        varchar(64)                        not null comment '书名',
    author           varchar(64)                        null comment '作者',
    img              varchar(256)                       null comment '书籍图片',
    publisher        varchar(256)                       null comment '出版社',
    introduction     varchar(512)                       null comment '简介',
    category         varchar(16)                        null,
    in_library_total bigint                             not null comment '在馆册数',
    create_time      datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete        tinyint  default 0                 not null comment '是否删除',
    price            double                             null comment '单价',
    total            bigint                             not null comment '馆藏总数'
)
    comment '图书表';

