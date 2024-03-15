# 数据库初始化

-- 创建库
create database if not exists LvJiBI;

-- 切换库
use LvJiBI;

-- 用户表
create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
)
    comment '用户' collate = utf8mb4_unicode_ci;

create index idx_userAccount
    on user (userAccount);

-- 图表信息表
create table chart
(
    id         bigint auto_increment comment 'id'
        primary key,
    `name`     varchar(128)                       null comment '图表名称',
    goal       text                               null comment '分析目标',
    chartData  text                               null comment '图标数据',
    chartType  varchar(128)                       null comment '图标类型',
    genChart   text                               null comment '生成的图标数据',
    genResult  text                               null comment '生成的分析结论',
    userId     bigint                             not null comment '创建用户 Id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '图标信息表' collate = utf8mb4_unicode_ci;
