-- 创建数据库
CREATE DATABASE IF NOT EXISTS veggie_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE veggie_db;

-- 创建蔬菜表
CREATE TABLE IF NOT EXISTS vegetable (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    name VARCHAR(100) NOT NULL COMMENT '蔬菜名称',
    category VARCHAR(50) COMMENT '分类（叶菜类、根茎类、瓜果类等）',
    price DECIMAL(10, 2) COMMENT '价格',
    stock INT DEFAULT 0 COMMENT '库存',
    description TEXT COMMENT '描述',
    origin VARCHAR(100) COMMENT '产地',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted INT DEFAULT 0 COMMENT '逻辑删除标志（0-未删除，1-已删除）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='蔬菜信息表';

-- 插入测试数据
INSERT INTO vegetable (name, category, price, stock, description, origin) VALUES
('大白菜', '叶菜类', 2.50, 100, '新鲜大白菜，口感脆嫩', '山东'),
('小白菜', '叶菜类', 3.00, 80, '嫩绿小白菜，营养丰富', '本地'),
('菠菜', '叶菜类', 4.50, 60, '新鲜菠菜，富含铁质', '河北'),
('土豆', '根茎类', 2.00, 200, '优质土豆，口感绵软', '内蒙古'),
('胡萝卜', '根茎类', 3.50, 150, '新鲜胡萝卜，甜脆可口', '新疆'),
('白萝卜', '根茎类', 2.80, 120, '大白萝卜，清脆爽口', '河南'),
('西红柿', '瓜果类', 5.00, 90, '红熟西红柿，酸甜多汁', '山东'),
('黄瓜', '瓜果类', 4.00, 110, '新鲜黄瓜，清脆爽口', '本地'),
('茄子', '瓜果类', 3.80, 70, '紫皮茄子，肉质细嫩', '河北'),
('青椒', '椒类', 4.50, 85, '新鲜青椒，微辣爽口', '山东'),
('辣椒', '椒类', 6.00, 50, '红辣椒，辣味十足', '湖南'),
('洋葱', '根茎类', 2.20, 180, '新鲜洋葱，辛辣提味', '甘肃'),
('芹菜', '叶菜类', 3.50, 95, '脆嫩芹菜，清香可口', '本地'),
('西兰花', '花菜类', 8.00, 40, '新鲜西兰花，营养丰富', '云南'),
('花菜', '花菜类', 5.50, 55, '白色花菜，口感细腻', '浙江');
