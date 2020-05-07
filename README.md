# travel-web

##基础准备
###需要安装lombok插件!!
idea 插件时长,搜索lombok插件,安装,用ASM阶段生成使用get,set

###日志路径,根据自己本地情况,调整logback日志路径

logback-custom.xml 文件中,修改 属性"logPath"对应value路径地址,默认日志地址是:/web/

###单元测试
见文件:UserControllerTest,其是单元测试
###配置文件
默认单元化测试,以及本地启动,默认使用的配置是dev的配置文件,如见:application-dev.yml,application-dao-dev.yml

##数据库相关
###数据库
使用的mysql作为数据库
###数据库自定义sql
举例:UserInfoMapper.xml ,UserInfoMapper 可以增加接口,用于自定义sql编写,具体见mybatis的用法
###修改数据库连接配置文件
application-dao-dev.yml  文件里的用户名和密码,自己修改下,根据实际情况
###建表sql,自己创建,可以使用travel作为数据库,或者自己调整,然后改下dao模块的配置文件即可

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