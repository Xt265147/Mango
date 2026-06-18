use mango;
drop table if exists `sys_menu`;

create table `sys_menu`
(
    `id`               bigint(20) not null auto_increment comment '编号',
    `name`             varchar(50)  default null comment '菜单名称',
    `parent_id`        bigint(20)   default null comment '父菜单ID，一级菜单为0',
    `url`              varchar(200) default null comment '菜单URL',
    `perms`            varchar(500) default null comment '授权(多个用逗号分隔，如：user:list,user:create)',
    `type`             int(11)      default null comment '类型   0：目录   1：菜单   2：按钮',
    `icon`             varchar(50)  default null comment '菜单图标',
    `order_num`        int(11)      default null comment '排序',
    `create_by`        varchar(50)  default null comment '创建人',
    `create_time`      datetime     default null comment '创建时间',
    `last_update_by`   varchar(50)  default null comment '更新人',
    `last_update_time` datetime     default null comment '更新时间',
    `del_flag`         tinyint(4)   default '0' comment '是否删除  -1：已删除  0：正常',
    primary key (`id`)
) engine = innodb
  auto_increment = 31
  default charset = utf8mb3 comment ='菜单管理';
