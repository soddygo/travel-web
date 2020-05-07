# travel-web"

##需要安装lombok插件!!
idea 插件时长,搜索lombok插件,安装,用ASM阶段生成使用get,set

##日志路径,根据自己本地情况,调整logback日志路径

logback-custom.xml 文件中,修改 属性"logPath"对应value路径地址,默认日志地址是:/web/

##单元测试
见文件:UserControllerTest,其是单元测试

##建表sql

```sql
create database travel;
use travel;

create table user_info
(
    id            bigint auto_increment comment '主键id'
        primary key,

    user_nick     varchar(50)                          null comment '用户昵称',
    user_name     varchar(50)                          null comment '用户姓名',
    phone_no      varchar(50)                          null comment '手机号',
    email         varchar(50)                          null comment 'email',
    password      varchar(50)                          null comment 'password',

    created       datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    creator_id    bigint                               not null comment '创建者ID',
    creator_name  varchar(50)                          not null comment '创建人',
    modified      datetime                             null on update CURRENT_TIMESTAMP comment '更新时间',
    modifier_id   bigint                               null comment '修改者ID',
    modifier_name varchar(50)                          null comment '修改人',
    yn            tinyint(1) default 1                 not null comment '是否有效,1:有效;-1:无效'
)
    comment '用户信息';
```