-- RBAC权限系统初始化脚本
USE veggie_db;

-- 删除旧表
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role_permission;
DROP TABLE IF EXISTS role_menu;
DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_permission;
DROP TABLE IF EXISTS sys_menu;

-- 创建用户表
CREATE TABLE sys_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    nickname VARCHAR(50) COMMENT '昵称',
    avatar VARCHAR(500) COMMENT '头像',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '手机号',
    status TINYINT(1) DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted INT DEFAULT 0 COMMENT '逻辑删除标志',
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 创建角色表
CREATE TABLE sys_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    role_code VARCHAR(50) NOT NULL UNIQUE COMMENT '角色编码',
    role_name VARCHAR(50) NOT NULL COMMENT '角色名称',
    description VARCHAR(200) COMMENT '描述',
    status TINYINT(1) DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted INT DEFAULT 0 COMMENT '逻辑删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';

-- 创建权限表
CREATE TABLE sys_permission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    perm_code VARCHAR(100) NOT NULL UNIQUE COMMENT '权限编码',
    perm_name VARCHAR(100) NOT NULL COMMENT '权限名称',
    description VARCHAR(200) COMMENT '描述',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted INT DEFAULT 0 COMMENT '逻辑删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限表';

-- 创建菜单表
CREATE TABLE sys_menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    parent_id BIGINT DEFAULT 0 COMMENT '父菜单ID，0为顶级菜单',
    menu_name VARCHAR(50) NOT NULL COMMENT '菜单名称',
    menu_code VARCHAR(50) COMMENT '菜单编码',
    path VARCHAR(200) COMMENT '路由路径',
    component VARCHAR(200) COMMENT '组件路径',
    icon VARCHAR(50) COMMENT '图标',
    sort_order INT DEFAULT 0 COMMENT '排序',
    menu_type TINYINT(1) DEFAULT 1 COMMENT '类型：1-菜单，2-按钮',
    status TINYINT(1) DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted INT DEFAULT 0 COMMENT '逻辑删除标志',
    INDEX idx_parent_id (parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单表';

-- 创建用户角色关联表
CREATE TABLE user_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_user_role (user_id, role_id),
    INDEX idx_user_id (user_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 创建角色权限关联表
CREATE TABLE role_permission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    permission_id BIGINT NOT NULL COMMENT '权限ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_role_perm (role_id, permission_id),
    INDEX idx_role_id (role_id),
    INDEX idx_permission_id (permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- 创建角色菜单关联表
CREATE TABLE role_menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    menu_id BIGINT NOT NULL COMMENT '菜单ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_role_menu (role_id, menu_id),
    INDEX idx_role_id (role_id),
    INDEX idx_menu_id (menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联表';

-- 插入初始数据

-- 插入管理员用户（密码：123456，已加密）
INSERT INTO sys_user (id, username, password, nickname, email, phone, status) VALUES
(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', 'admin@veggie.com', '13800138000', 1);

-- 插入角色
INSERT INTO sys_role (id, role_code, role_name, description, status) VALUES
(1, 'super_admin', '超级管理员', '拥有所有权限', 1),
(2, 'admin', '管理员', '拥有大部分权限', 1),
(3, 'user', '普通用户', '拥有基础权限', 1);

-- 插入权限
INSERT INTO sys_permission (id, perm_code, perm_name, description) VALUES
(1, 'user:view', '用户查看', '查看用户列表'),
(2, 'user:add', '用户添加', '添加新用户'),
(3, 'user:edit', '用户编辑', '编辑用户信息'),
(4, 'user:delete', '用户删除', '删除用户'),
(5, 'role:view', '角色查看', '查看角色列表'),
(6, 'role:add', '角色添加', '添加新角色'),
(7, 'role:edit', '角色编辑', '编辑角色信息'),
(8, 'role:delete', '角色删除', '删除角色'),
(9, 'menu:view', '菜单查看', '查看菜单列表'),
(10, 'menu:add', '菜单添加', '添加新菜单'),
(11, 'menu:edit', '菜单编辑', '编辑菜单信息'),
(12, 'menu:delete', '菜单删除', '删除菜单'),
(13, 'veggie:view', '蔬果查看', '查看蔬果列表'),
(14, 'veggie:add', '蔬果添加', '添加蔬果'),
(15, 'veggie:edit', '蔬果编辑', '编辑蔬果'),
(16, 'veggie:delete', '蔬果删除', '删除蔬果');

-- 插入菜单
INSERT INTO sys_menu (id, parent_id, menu_name, menu_code, path, component, icon, sort_order, menu_type, status) VALUES
(1, 0, '系统管理', 'system', '/system', NULL, 'SettingOutlined', 1, 1, 1),
(2, 1, '用户管理', 'user', '/system/user', 'system/user/index', 'UserOutlined', 1, 1, 1),
(3, 1, '角色管理', 'role', '/system/role', 'system/role/index', 'TeamOutlined', 2, 1, 1),
(4, 1, '菜单管理', 'menu', '/system/menu', 'system/menu/index', 'MenuOutlined', 3, 1, 1),
(5, 0, '蔬果管理', 'veggie', '/veggie', NULL, 'ShoppingOutlined', 2, 1, 1),
(6, 5, '蔬果列表', 'veggie-list', '/veggie/list', 'veggie/list/index', 'UnorderedListOutlined', 1, 1, 1),
(7, 5, '应季推荐', 'veggie-season', '/veggie/season', 'veggie/season/index', 'CalendarOutlined', 2, 1, 1),
(8, 5, '城市推荐', 'veggie-city', '/veggie/city', 'veggie/city/index', 'EnvironmentOutlined', 3, 1, 1);

-- 关联用户角色
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

-- 关联角色权限（超级管理员拥有所有权限）
INSERT INTO role_permission (role_id, permission_id) 
SELECT 1, id FROM sys_permission;

-- 关联角色菜单（超级管理员拥有所有菜单）
INSERT INTO role_menu (role_id, menu_id)
SELECT 1, id FROM sys_menu;
