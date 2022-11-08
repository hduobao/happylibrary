CREATE TABLE USER
(
    id           BIGINT AUTO_INCREMENT COMMENT 'id'
        PRIMARY KEY,
    username     VARCHAR(256)                       NULL COMMENT '昵称',
    user_account  INT(32)                       NULL COMMENT '学号',
    avatar    VARCHAR(1024)                      NULL COMMENT '用户头像',
    gender       TINYINT                            NULL COMMENT '性别',
    user_password VARCHAR(512)                       NOT NULL COMMENT '密码',
    phone        VARCHAR(128)                       NULL COMMENT '电话',
    email        VARCHAR(512)                       NULL COMMENT '邮箱',
    violation 	  INT 				     NOT NULL DEFAULT 0 COMMENT '违约次数',
    user_status   INT      DEFAULT 0                 NOT NULL COMMENT '用户状态 0-正常',
    user_role     INT      DEFAULT 0                 NOT NULL COMMENT '用户角色：0=普通用户 1=管理员',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除'
)
    COMMENT '用户';