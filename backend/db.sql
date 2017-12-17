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




-- 以下是统计时动态创建*************************************************


-- 代表答题次数统计表
create table `sponsor_statistics_3` (
  `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `mobile`      VARCHAR(11)     NOT NULL
  COMMENT '代表手机号',
  `count`       INT             NOT NULL DEFAULT 0
  COMMENT '答题次数',
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- 组织结构信息表
create table `organization` (
  `id`       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `name`     VARCHAR(32)     NULL
  COMMENT '代表姓名',
  `mobile`   VARCHAR(11)     NULL
  COMMENT '代表手机号',
  `tag`      VARCHAR(32)     NULL
  COMMENT '新分组',
  `zone`     VARCHAR(32)     NULL
  COMMENT '大区',
  `area`     VARCHAR(32)     NULL
  COMMENT '小区',
  `province` VARCHAR(32)     NULL
  COMMENT '大区所属的省份',
  `position` VARCHAR(32)     NULL
  COMMENT '职位',
  `ext1`     VARCHAR(32)     NULL
  COMMENT '扩展1',
  `ext2`     VARCHAR(32)     NULL
  COMMENT '扩展2',
  `ext3`     VARCHAR(32)     NULL
  COMMENT '扩展3',
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

create table `organization_statistics_3` (
  `id`       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `count`    INT             NULL DEFAULT 0
  COMMENT '答题次数',
  `ratio`    VARCHAR(32)     NULL DEFAULT '0.00%'
  COMMENT '完成率(count/15 * 100%)',
  `name`     VARCHAR(32)     NULL
  COMMENT '代表姓名',
  `mobile`   VARCHAR(11)     NULL
  COMMENT '代表手机号',
  `tag`      VARCHAR(32)     NULL
  COMMENT '新分组',
  `zone`     VARCHAR(32)     NULL
  COMMENT '大区',
  `area`     VARCHAR(32)     NULL
  COMMENT '小区',
  `province` VARCHAR(32)     NULL
  COMMENT '代表所属的省份',
  `position` VARCHAR(32)     NULL
  COMMENT '职位',
  `ext1`     VARCHAR(32)     NULL
  COMMENT '扩展1',
  `ext2`     VARCHAR(32)     NULL
  COMMENT '扩展2',
  `ext3`     VARCHAR(32)     NULL
  COMMENT '扩展3',
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- area地区统计结果表
create table `organization_statistics_3_area` (
  `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `area`          VARCHAR(32)     NULL
  COMMENT '小区',
  `sponsor_count` INT             NULL DEFAULT 0
  COMMENT '地区代表总数',
  `count`         INT             NULL DEFAULT 0
  COMMENT '答题次数',
  `ratio`         VARCHAR(32)     NULL DEFAULT '0.00%'
  COMMENT '完成率，count/(spnsor_count*15) * 100%',
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- zone大区统计结果表
create table `organization_statistics_3_zone` (
  `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `zone`          VARCHAR(32)     NULL
  COMMENT '大区',
  `sponsor_count` INT             NULL DEFAULT 0
  COMMENT '地区代表总数',
  `count`         INT             NULL DEFAULT 0
  COMMENT '答题次数',
  `ratio`         VARCHAR(32)     NULL DEFAULT '0.00%'
  COMMENT '完成率，count/(spnsor_count*15) * 100%',
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- 地区经理统计结果表
create table `organization_statistics_3_AM` (
  `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `am_name`       VARCHAR(32)     NULL
  COMMENT '地区经理姓名',
  `am_mobile`     VARCHAR(11)     NULL
  COMMENT '地区经理手机号',
  `count`         INT             NULL DEFAULT 0
  COMMENT '答题次数',
  `ratio`         VARCHAR(32)     NULL DEFAULT '0.00%'
  COMMENT '完成率，count/15 * 100%',
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- 省份统计结果表
create table `organization_statistics_3_province` (
  `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `province` VARCHAR(32)     NULL
  COMMENT '省份名称',
  `sponsor_count` INT             NULL DEFAULT 0
  COMMENT '省份内代表总数',
  `count`         INT             NULL DEFAULT 0
  COMMENT '答题次数',
  `ratio`         VARCHAR(32)     NULL DEFAULT '0.00%'
  COMMENT '完成率，count/(spnsor_count*15) * 100%',
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;

-- 第三套题答题结果中关联的代表手机号表
create table `sponsor_statistics_mobile_3` (
  `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `name`     VARCHAR(32)     NULL
  COMMENT '代表姓名',
  `mobile`   VARCHAR(11)     NULL
  COMMENT '代表手机号',
  PRIMARY KEY (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8;