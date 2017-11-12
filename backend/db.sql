drop database if exists `loveself`;
create database `loveself`;
use `loveself`;

-- 二维码信息表
create table `qrcode` (
  `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `scene`      BIGINT          NOT NULL
  COMMENT '场景值，必须是数字',
  `type`       TINYINT         NOT NULL
  COMMENT '类型,0: 永久, 1: 临时',
  `name`       VARCHAR(256)    NOT NULL
  COMMENT '图片名称',
  `path`       VARCHAR(256)    NOT NULL
  COMMENT '图片路径',
  `expire`     INT             NULL
  COMMENT '临时二维码有效时间',
  `created_at` DATETIME        NULL,
  `updated_at` DATETIME        NULL,
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- 用户信息表
create table `user` (
  `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `openid`     VARCHAR(64)     NOT NULL
  COMMENT '微信openid',
  `nickname`   VARCHAR(32)     NULL
  COMMENT '用户昵称',
  `avatar`     VARCHAR(256)    NULL
  COMMENT '用户微信头像',
  `sex`        TINYINT         NULL
  COMMENT '性别',
  `country`    VARCHAR(32)     NULL
  COMMENT '国家',
  `province`   VARCHAR(32)     NULL
  COMMENT '省份',
  `city`       VARCHAR(32)     NULL
  COMMENT '城市',
  `created_at` DATETIME        NULL,
  `updated_at` DATETIME        NULL,
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- 代表信息表
create table `sponsor` (
  `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `mobile`     VARCHAR(11)     NOT NULL
  COMMENT '微信openid',
  `openid`     VARCHAR(64)     NULL
  COMMENT '微信openid',
  `nickname`   VARCHAR(32)     NULL
  COMMENT '用户昵称',
  `avatar`     VARCHAR(256)    NULL
  COMMENT '用户微信头像',
  `sex`        TINYINT         NULL
  COMMENT '性别',
  `country`    VARCHAR(32)     NULL
  COMMENT '国家',
  `province`   VARCHAR(32)     NULL
  COMMENT '省份',
  `city`       VARCHAR(32)     NULL
  COMMENT '城市',
  `created_at` DATETIME        NULL,
  `updated_at` DATETIME        NULL,
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- 用户和代表关联关系表
create table `user_relation` (
  `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `id_sponsor` BIGINT UNSIGNED NOT NULL
  COMMENT '代表信息表ID',
  `id_user`    BIGINT UNSIGNED NOT NULL
  COMMENT '用户信息表ID',
  `created_at` DATETIME        NULL,
  `updated_at` DATETIME        NULL,
  FOREIGN KEY (`id_sponsor`) REFERENCES sponsor (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`id_user`) REFERENCES user (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- 题库信息表
create table `question` (
  `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `id_sponsor` BIGINT UNSIGNED NULL
  COMMENT '代表信息表ID',
  `id_qrcode`  BIGINT UNSIGNED NOT NULL
  COMMENT '二维码表ID',
  `data`       TEXT            NOT NULL
  COMMENT '题目信息Json字符串',
  `created_at` DATETIME        NULL,
  `updated_at` DATETIME        NULL,
  FOREIGN KEY (`id_qrcode`) REFERENCES qrcode (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;


-- 题库配置信息表
create table `question_config` (
  `id`                              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `id_question`                     BIGINT UNSIGNED NOT NULL
  COMMENT '题库信息表ID',
  `background_color`                VARCHAR(10)     NOT NULL
  COMMENT '页面背景色',
  `page_discription_color`          VARCHAR(10)     NOT NULL
  COMMENT '页面描述文字的颜色',
  `question_active_color`           VARCHAR(10)     NOT NULL
  COMMENT '题干、题目选项选中背景色',
  `question_option_color`           VARCHAR(10)     NOT NULL
  COMMENT '题目选项背景色',
  `bar_title_text`                  VARCHAR(32)     NULL
  COMMENT '页面顶部Bar显示文字',
  `banner_text`                     VARCHAR(32)     NULL
  COMMENT 'banner文字',
  `banner_pic_name`                 VARCHAR(256)    NULL
  COMMENT 'banner图片路径',
  `home_page_discription`           VARCHAR(256)    NULL
  COMMENT '首页描述文字',
  `home_page_pic_name`              VARCHAR(256)    NULL
  COMMENT '首页图片路径',
  `end_page_discription`            VARCHAR(256)    NULL
  COMMENT '结束页描述文字',
  `end_page_pic_name`               VARCHAR(256)    NULL
  COMMENT '结束页图片路径',
  `created_at`                      DATETIME        NULL,
  `updated_at`                      DATETIME        NULL,
  FOREIGN KEY (`id_question`) REFERENCES question (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- 用户答题结果表
create table `user_answer` (
  `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `id_user`     BIGINT UNSIGNED NOT NULL
  COMMENT '用户信息表ID',
  `id_question` BIGINT UNSIGNED NOT NULL
  COMMENT '题库信息表ID',
  `data`        TEXT            NOT NULL
  COMMENT '用户答题结果信息Json字符串',
  `created_at`  DATETIME        NULL,
  `updated_at`  DATETIME        NULL,
  FOREIGN KEY (`id_user`) REFERENCES user (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`id_question`) REFERENCES question (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;