-- 创建数据库
CREATE DATABASE IF NOT EXISTS veggie_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE veggie_db;

-- 删除旧表（如果存在）
DROP TABLE IF EXISTS city_recommendation;
DROP TABLE IF EXISTS veggie_fruit;
DROP TABLE IF EXISTS vegetable;

-- 创建蔬果表
CREATE TABLE veggie_fruit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    name VARCHAR(100) NOT NULL COMMENT '名称',
    type VARCHAR(20) COMMENT '类型：vegetable-蔬菜，fruit-水果',
    price DECIMAL(10, 2) COMMENT '价格',
    is_local TINYINT(1) DEFAULT 0 COMMENT '是否本地：0-否，1-是',
    season_info VARCHAR(200) COMMENT '季节信息',
    season_start INT COMMENT '季节开始月份（1-12）',
    season_end INT COMMENT '季节结束月份（1-12）',
    cooking_tip VARCHAR(200) COMMENT '烹饪建议',
    nutrition VARCHAR(200) COMMENT '营养价值',
    sweet_level VARCHAR(20) COMMENT '甜度等级',
    image VARCHAR(500) COMMENT '图片URL',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted INT DEFAULT 0 COMMENT '逻辑删除标志（0-未删除，1-已删除）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='蔬果信息表';

-- 创建城市推荐表
CREATE TABLE city_recommendation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    city_name VARCHAR(50) NOT NULL COMMENT '城市名称',
    veggie_fruit_id BIGINT NOT NULL COMMENT '蔬果ID',
    reason VARCHAR(200) COMMENT '推荐原因',
    sort_order INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted INT DEFAULT 0 COMMENT '逻辑删除标志（0-未删除，1-已删除）',
    INDEX idx_city_name (city_name),
    INDEX idx_veggie_fruit_id (veggie_fruit_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='城市蔬果推荐表';

-- 插入蔬菜数据（带季节月份）
INSERT INTO veggie_fruit (name, type, price, is_local, season_info, season_start, season_end, cooking_tip, nutrition, image) VALUES
('春笋', 'vegetable', 9.80, 1, '春季限定', 3, 5, '油焖、炖肉', '高纤维、低脂', 'https://images.unsplash.com/photo-1691053803737-7fbe52f5d70e?fm=jpg&q=60&w=3000'),
('菠菜', 'vegetable', 4.50, 1, '春菠最甜', 3, 5, '凉拌、做汤', '补铁、维生素K', 'https://images.unsplash.com/photo-1576045057995-568f588f82fb?q=80&w=300'),
('草头', 'vegetable', 12.00, 1, '时令野菜', 3, 4, '酒炒、圈子', '清热解毒', 'https://images.unsplash.com/photo-1596363505729-4190a9506133?q=80&w=300'),
('胡萝卜', 'vegetable', 8.00, 0, '清明前', 3, 4, '馄饨、炒蛋', '明目、降压', 'https://plus.unsplash.com/premium_photo-1724849305142-498abc1f7b89?w=600'),
('芦笋', 'vegetable', 15.00, 0, '春季嫩芽', 4, 6, '清炒、烤制', '抗癌、高蛋白', 'https://images.unsplash.com/photo-1518977676601-b53f82aba655?q=80&w=300'),
('蚕豆', 'vegetable', 6.50, 1, '本地上市', 5, 6, '葱油、煮饭', '健脑、补钙', 'https://images.unsplash.com/photo-1605402966404-ec40b9bd5009?w=600'),
('蒜薹', 'vegetable', 7.50, 1, '春季时令', 4, 5, '蒜薹炒腊肉', '杀菌、通便', 'https://copyright.bdstatic.com/vcg/creative/252949a84509212b11489c19d61ffdfe.jpg'),
('白菜', 'vegetable', 3.00, 1, '冬季时令', 11, 2, '炒、炖、做汤', '维生素丰富', 'https://plus.unsplash.com/premium_photo-1702489575687-204529449d94?w=600'),
('西红柿', 'vegetable', 5.50, 1, '夏季时令', 6, 8, '炒蛋、做汤', '番茄红素', 'https://images.unsplash.com/photo-1592924357228-91a4daadcfea?q=80&w=300'),
('冬瓜', 'vegetable', 3.80, 1, '夏季时令', 7, 9, '炖汤、红烧', '清热解暑', 'https://images.unsplash.com/photo-1595456982104-14cc630c2d22?q=80&w=300');

-- 插入水果数据（带季节月份）
INSERT INTO veggie_fruit (name, type, price, is_local, season_info, season_start, season_end, cooking_tip, nutrition, sweet_level, image) VALUES
('草莓', 'fruit', 18.80, 1, '甜度巅峰', 3, 5, '直接吃、做酱', '维C之王', '⭐⭐⭐⭐', 'https://images.unsplash.com/photo-1518635017498-87f514b751ba?q=80&w=300'),
('圣女果', 'fruit', 6.80, 1, '四季常供', 1, 12, '沙拉、零食', '番茄红素', '⭐⭐⭐', 'https://images.unsplash.com/photo-1570543375343-63fe3d67761b?w=600'),
('枇杷', 'fruit', 22.00, 0, '早春第一果', 4, 5, '直接吃、熬膏', '润肺止咳', '⭐⭐⭐⭐', 'https://images.unsplash.com/photo-1756670969537-7d3d521d9128?w=600'),
('菠萝', 'fruit', 9.90, 0, '香气浓郁', 4, 6, '盐水泡、炒菜', '助消化', '⭐⭐⭐⭐⭐', 'https://images.unsplash.com/photo-1550258987-190a2d41a8ba?q=80&w=300'),
('桑葚', 'fruit', 15.00, 0, '短暂上市', 5, 6, '直接吃、泡酒', '花青素', '⭐⭐⭐', 'https://images.unsplash.com/photo-1596363505729-4190a9506133?q=80&w=300'),
('樱桃', 'fruit', 35.00, 0, '早熟品种', 5, 6, '直接吃', '含铁丰富', '⭐⭐⭐⭐⭐', 'https://plus.unsplash.com/premium_photo-1688671923138-ff74e0f9a810?w=600'),
('西瓜', 'fruit', 3.50, 1, '夏季消暑', 6, 8, '直接吃、榨汁', '解暑利尿', '⭐⭐⭐⭐⭐', 'https://images.unsplash.com/photo-1587049352846-4a222e784d38?q=80&w=300'),
('桃子', 'fruit', 8.80, 1, '夏季时令', 6, 8, '直接吃、做罐头', '润肠通便', '⭐⭐⭐⭐', 'https://images.unsplash.com/photo-1629753250291-979952613877?q=80&w=300'),
('葡萄', 'fruit', 12.50, 0, '秋季时令', 8, 10, '直接吃、酿酒', '抗氧化', '⭐⭐⭐⭐', 'https://images.unsplash.com/photo-1537640538965-1756cd58090e?q=80&w=300'),
('橙子', 'fruit', 6.80, 0, '冬季时令', 11, 2, '直接吃、榨汁', '维C丰富', '⭐⭐⭐⭐', 'https://images.unsplash.com/photo-1611080626919-7cf5a9dbab5b?q=80&w=300');

-- 插入上海城市推荐数据
INSERT INTO city_recommendation (city_name, veggie_fruit_id, reason, sort_order) VALUES
('上海', 3, '酒香草头正嫩', 1),
('上海', 7, '蒜薹炒腊肉', 2),
('上海', 9, '南汇草莓红了', 3),
('上海', 4, '清明前最鲜', 4),
('上海', 8, '营养又好吃', 5);
