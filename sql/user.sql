-- auto-generated definition
create table user
(
    id            bigint auto_increment comment 'id'
        primary key,
    username      varchar(256)                       null comment '昵称',
    user_account  bigint                             not null comment '学号',
    avatar        varchar(1024)                      null comment '用户头像',
    gender        tinyint                            null comment '性别',
    user_password varchar(512)                       not null comment '密码',
    phone         varchar(128)                       null comment '电话',
    email         varchar(512)                       null comment '邮箱',
    violation     int      default 0                 not null comment '违约次数',
    user_status   int      default 1                 not null comment '用户状态 1-正常',
    user_role     int      default 0                 not null comment '用户角色：0=普通用户 1=管理员',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0                 not null comment '是否删除',
    constraint user_user_account_uindex
        unique (user_account)
)
    comment '用户';

