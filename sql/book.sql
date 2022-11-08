CREATE TABLE book(
                     id           BIGINT AUTO_INCREMENT COMMENT 'id'
                         PRIMARY KEY,
                     book_name VARCHAR(64) NOT NULL COMMENT '书名',
                     author VARCHAR(64) COMMENT '作者',
                     price DOUBLE COMMENT '单价',
                     publisher VARCHAR(256) COMMENT '出版社',
                     introduction VARCHAR(512) COMMENT '简介',
                     total BIGINT NOT NULL COMMENT '馆藏总数',
                     in_library_total BIGINT NOT NULL COMMENT '在馆册数',
                     create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
                     update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                     is_delete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) COMMENT '图书表'