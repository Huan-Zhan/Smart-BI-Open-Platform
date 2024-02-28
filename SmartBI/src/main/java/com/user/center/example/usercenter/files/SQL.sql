-- auto-generated definition
create table usercharts
(
    Id          bigint auto_increment
        primary key,
    UserName    varchar(512)                       not null comment '创建表时的用户名',
    UserId      mediumtext                         null comment '用户唯一id , 八位有效数字',
    CreateDate  datetime default CURRENT_TIMESTAMP null comment '自动添加',
    ChartName   varchar(512)                       not null comment '图标名称',
    AnalysisAim varchar(1024)                      null,
    ChartType   varchar(512)                       null,
    IsDelete    int      default 0                 null comment '0-正常
1-删除'
)
    comment '用户图标总表';

