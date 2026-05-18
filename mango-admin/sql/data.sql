-- 机构数据
INSERT INTO sys_dept (id, name, parent_id, order_num, create_by, create_time) VALUES
(1, '总公司', 0, 1, 'admin', NOW()),
(2, '技术部', 1, 1, 'admin', NOW()),
(3, '产品部', 1, 2, 'admin', NOW()),
(4, '财务部', 1, 3, 'admin', NOW()),
(5, '研发一组', 2, 1, 'admin', NOW()),
(6, '研发二组', 2, 2, 'admin', NOW());

-- 角色数据
INSERT INTO sys_role (id, name, remark, create_by, create_time) VALUES
(1, '管理员', '系统管理员，拥有所有权限', 'admin', NOW()),
(2, '普通用户', '普通用户，拥有基础权限', 'admin', NOW()),
(3, '部门经理', '部门经理权限', 'admin', NOW());

-- 用户数据（密码：123456，加密后）
INSERT INTO sys_user (id, name, nick_name, password, salt, email, mobile, status, dept_id, create_by, create_time) VALUES
(1, 'admin', '管理员', 'e10adc3949ba59abbe56e057f20f883e', 'mango', 'admin@mango.com', '13800138001', 1, 1, 'admin', NOW()),
(2, 'zhangsan', '张三', 'e10adc3949ba59abbe56e057f20f883e', 'mango', 'zhangsan@mango.com', '13800138002', 1, 5, 'admin', NOW()),
(3, 'lisi', '李四', 'e10adc3949ba59abbe56e057f20f883e', 'mango', 'lisi@mango.com', '13800138003', 1, 6, 'admin', NOW()),
(4, 'wangwu', '王五', 'e10adc3949ba59abbe56e057f20f883e', 'mango', 'wangwu@mango.com', '13800138004', 1, 3, 'admin', NOW()),
(5, 'zhaoliu', '赵六', 'e10adc3949ba59abbe56e057f20f883e', 'mango', 'zhaoliu@mango.com', '13800138005', 1, 4, 'admin', NOW());

-- 用户角色关联
INSERT INTO sys_user_role (user_id, role_id, create_by, create_time) VALUES
(1, 1, 'admin', NOW()),
(2, 2, 'admin', NOW()),
(3, 2, 'admin', NOW()),
(4, 3, 'admin', NOW()),
(5, 2, 'admin', NOW());

-- 菜单数据
INSERT INTO sys_menu (id, name, parent_id, url, perms, type, icon, order_num, create_by, create_time) VALUES
(1, '系统管理', 0, NULL, NULL, 0, 'system', 1, 'admin', NOW()),
(2, '用户管理', 1, '/sys/user', 'sys:user:list,sys:user:add,sys:user:edit,sys:user:del', 1, 'user', 1, 'admin', NOW()),
(3, '角色管理', 1, '/sys/role', 'sys:role:list,sys:role:add,sys:role:edit,sys:role:del', 1, 'role', 2, 'admin', NOW()),
(4, '菜单管理', 1, '/sys/menu', 'sys:menu:list,sys:menu:add,sys:menu:edit,sys:menu:del', 1, 'menu', 3, 'admin', NOW()),
(5, '机构管理', 1, '/sys/dept', 'sys:dept:list,sys:dept:add,sys:dept:edit,sys:dept:del', 1, 'dept', 4, 'admin', NOW()),
(6, '字典管理', 1, '/sys/dict', 'sys:dict:list,sys:dict:add,sys:dict:edit,sys:dict:del', 1, 'dict', 5, 'admin', NOW()),
(7, '系统配置', 1, '/sys/config', 'sys:config:list,sys:config:add,sys:config:edit,sys:config:del', 1, 'config', 6, 'admin', NOW()),
(8, '系统监控', 0, NULL, NULL, 0, 'monitor', 2, 'admin', NOW()),
(9, '操作日志', 8, '/sys/log', 'sys:log:list', 1, 'log', 1, 'admin', NOW()),
(10, '登录日志', 8, '/sys/loginLog', 'sys:loginLog:list', 1, 'loginLog', 2, 'admin', NOW());

-- 角色菜单关联
INSERT INTO sys_role_menu (role_id, menu_id, create_by, create_time) VALUES
(1, 1, 'admin', NOW()), (1, 2, 'admin', NOW()), (1, 3, 'admin', NOW()),
(1, 4, 'admin', NOW()), (1, 5, 'admin', NOW()), (1, 6, 'admin', NOW()),
(1, 7, 'admin', NOW()), (1, 8, 'admin', NOW()), (1, 9, 'admin', NOW()),
(1, 10, 'admin', NOW()),
(2, 1, 'admin', NOW()), (2, 2, 'admin', NOW()), (2, 8, 'admin', NOW()),
(2, 9, 'admin', NOW()), (2, 10, 'admin', NOW()),
(3, 1, 'admin', NOW()), (3, 2, 'admin', NOW()), (3, 5, 'admin', NOW()),
(3, 8, 'admin', NOW()), (3, 9, 'admin', NOW());

-- 字典数据
INSERT INTO sys_dict (value, label, type, description, sort, create_by, create_time) VALUES
('0', '禁用', 'user_status', '用户状态', 1, 'admin', NOW()),
('1', '正常', 'user_status', '用户状态', 2, 'admin', NOW()),
('0', '目录', 'menu_type', '菜单类型', 1, 'admin', NOW()),
('1', '菜单', 'menu_type', '菜单类型', 2, 'admin', NOW()),
('2', '按钮', 'menu_type', '菜单类型', 3, 'admin', NOW()),
('1', '男', 'sex', '性别', 1, 'admin', NOW()),
('2', '女', 'sex', '性别', 2, 'admin', NOW()),
('0', '否', 'yes_no', '是否', 1, 'admin', NOW()),
('1', '是', 'yes_no', '是否', 2, 'admin', NOW()),
('1', '在线', 'login_status', '登录状态', 1, 'admin', NOW()),
('2', '离线', 'login_status', '登录状态', 2, 'admin', NOW());

-- 系统配置
INSERT INTO sys_config (value, label, type, description, sort, create_by, create_time) VALUES
('Mango Admin', '系统名称', 'system', '系统显示名称', 1, 'admin', NOW()),
('1.0.0', '系统版本', 'system', '当前系统版本', 2, 'admin', NOW()),
('http://localhost:8001', '系统地址', 'system', '系统访问地址', 3, 'admin', NOW());

-- 初始化完成提示
SELECT '初始化数据完成' AS result;
