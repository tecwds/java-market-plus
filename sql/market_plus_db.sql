-- 中文名称：潘的大超市
-- 英文名称：Pan's Market Plus

-- MySQL 5.7

create database if not exists market_plus_db;

-- 需要使用数据库，否则建表语句失败
use market_plus_db;

# drop table if exists template;
# create table if not exists template
# (
#     gmt_created  datetime         not null comment '创建时间',
#     id           bigint unsigned  not null comment '表的 ID 主键',
#     gmt_modified datetime         not null comment '更新日期',
#     is_deleted   tinyint unsigned not null comment '逻辑删除',
#     is_enabled   tinyint unsigned not null comment '是否启用'
# )
#     comment '通用数据库模板' charset = utf8;



drop table if exists wb_category;
create table if not exists wb_category
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    name         varchar(20)      not null comment '分类名称',
    description  varchar(20)      null comment '分类描述',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用'
) comment '分类表' charset = utf8;



drop table if exists wb_goods;
create table if not exists wb_goods
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    name         varchar(40)      not null comment '商品名称',
    description  varchar(40)      null comment '商品描述',
    price        bigint unsigned  not null comment '商品价格',
    image        varchar(255)     not null comment '图片地址',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用'
) comment '商品表' charset = utf8;



drop table if exists wb_goods_category;
create table if not exists wb_goods_category
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    goods_id     bigint unsigned  not null,
    category_id  bigint unsigned  not null,
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用',
    constraint wb_goods_category_wb_category_id_fk
        foreign key (category_id) references wb_category (id),
    constraint wb_goods_category_wb_goods_id_fk
        foreign key (goods_id) references wb_goods (id)
) comment '商品分类表' charset = utf8;



drop table if exists wb_role;
create table if not exists wb_role
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    name         varchar(10)      not null comment '角色名称（英文）',
    type         int unsigned auto_increment comment '角色类型',
    description  varchar(20)      null comment '描述',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用',
    constraint wb_role_pk
        unique (type),
    constraint wb_role_pk_2
        unique (name)
) comment '角色表' charset = utf8;



drop table if exists wb_tag;
create table if not exists wb_tag
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    name         varchar(20)      not null comment '标签名称',
    description  varchar(20)      null comment '详细描述',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用'
) comment '标签表' charset = utf8;



drop table if exists wb_goods_tag;
create table if not exists wb_goods_tag
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    goods_id     bigint unsigned  not null,
    tag_id       bigint unsigned  not null,
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用',
    constraint wb_goods_tag_wb_goods_id_fk
        foreign key (goods_id) references wb_goods (id),
    constraint wb_goods_tag_wb_tag_id_fk
        foreign key (tag_id) references wb_tag (id)
) comment '商品标签关联表' charset = utf8;



drop table if exists wb_user;
create table if not exists wb_user
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    email        varchar(20)      not null comment '邮箱',
    password     varchar(80)      not null comment '密码',
    avatar       varchar(20)      null comment '头像地址',
    username     varchar(20)      null comment '用户名',
    nickname     varchar(20)      null comment '昵称',
    signature    varchar(20)      null comment '签名',
    gender       tinyint unsigned null comment '性别',
    role_name    varchar(10)      not null comment '角色名称',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用'
) comment '用户表' charset = utf8;



drop table if exists wb_address;
create table if not exists wb_address
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    user_id      bigint unsigned  null comment '关联用户 ID',
    email        varchar(20)      not null comment '邮箱',
    phone        varchar(11)      not null comment '电话',
    province     varchar(20)      not null comment '省',
    city         varchar(20)      not null comment '市',
    country      varchar(20)      null comment '区',
    detail       varchar(100)     null comment '详细地址',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用',
    constraint wb_address_wb_user_id_fk
        foreign key (user_id) references wb_user (id)
) comment '收货地址表' charset = utf8;



drop table if exists wb_cart;
create table if not exists wb_cart
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    user_id      bigint unsigned  not null comment '关联用户ID',
    goods_id     bigint unsigned  not null comment '商品 ID',
    count        int unsigned     not null comment '数量',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用',
    constraint wb_cart_wb_goods_id_fk
        foreign key (goods_id) references wb_goods (id),
    constraint wb_cart_wb_user_id_fk
        foreign key (user_id) references wb_user (id)
) comment '购物车表' charset = utf8;



drop table if exists wb_order;
create table if not exists wb_order
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    user_id      bigint unsigned  not null comment '关联用户 ID',
    goods_id     bigint unsigned  not null comment '关联商品ID',
    price        bigint unsigned  not null comment '单价',
    total        bigint unsigned  not null comment '总价',
    count        int unsigned     not null comment '数量',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用',
    constraint wb_order_wb_goods_id_fk
        foreign key (goods_id) references wb_goods (id),
    constraint wb_order_wb_user_id_fk
        foreign key (user_id) references wb_user (id)
) comment '订单表' charset = utf8;



drop table if exists wb_store;
create table if not exists wb_store
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    user_id      bigint unsigned  not null comment '所属用户',
    name         varchar(40)      not null comment '店名',
    description  varchar(100)     null comment '描述',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用',
    constraint wb_store_wb_user_id_fk
        foreign key (user_id) references wb_user (id)
) comment '商家表' charset = utf8;



drop table if exists wb_inventory;
create table if not exists wb_inventory
(
    id           bigint unsigned  not null comment '表的 ID 主键'
        primary key,
    store_id     bigint unsigned  not null comment '关联商店ID',
    goods_id     bigint unsigned  not null comment '关联商品 ID',
    price        bigint unsigned  not null comment '价格（冗余，加快访问）',
    count        int unsigned     not null comment '库存数量',
    gmt_created  datetime         not null comment '创建时间',
    gmt_modified datetime         not null comment '更新日期',
    is_deleted   tinyint unsigned not null comment '逻辑删除',
    is_enabled   tinyint unsigned not null comment '是否启用',
    constraint wb_inventory_wb_goods_id_fk
        foreign key (goods_id) references wb_goods (id),
    constraint wb_inventory_wb_store_id_fk
        foreign key (store_id) references wb_store (id)
) comment '库存表' charset = utf8;