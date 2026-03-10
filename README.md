# Veggie Backend 蔬菜管理系统

## 技术栈
- Spring Boot 2.3.7
- MyBatis-Plus 3.4.2
- MySQL 8.0
- Maven

## 项目结构
```
veggie-backend/
├── src/
│   └── main/
│       ├── java/com/veggie/
│       │   ├── VeggieApplication.java      # 启动类
│       │   ├── config/                     # 配置类
│       │   ├── controller/                 # 控制器层
│       │   ├── entity/                     # 实体类
│       │   ├── mapper/                     # Mapper接口
│       │   └── service/                    # 服务层
│       └── resources/
│           └── application.yml             # 配置文件
├── sql/
│   └── init.sql                            # 数据库初始化脚本
└── pom.xml                                 # Maven配置
```

## 数据库配置
- 地址: localhost:3306
- 数据库: veggie_db
- 用户名: root
- 密码: 123456

## 启动步骤

### 1. 初始化数据库
在MySQL中执行 sql/init.sql 脚本创建数据库和表：
```bash
mysql -u root -p < sql/init.sql
```

### 2. 编译启动项目
```bash
# 使用Maven Wrapper（推荐）
mvnw.cmd spring-boot:run

# 或先编译再运行
mvnw.cmd clean package
java -jar target/veggie-backend-1.0.0.jar
```

## API接口

### 测试接口
- GET http://localhost:8080/veggie/vegetable/test

### 查询所有蔬菜
- GET http://localhost:8080/veggie/vegetable/list

### 根据ID查询
- GET http://localhost:8080/veggie/vegetable/{id}

### 根据分类查询
- GET http://localhost:8080/veggie/vegetable/category/{category}

### 添加蔬菜
- POST http://localhost:8080/veggie/vegetable/add
- Content-Type: application/json
```json
{
    "name": "冬瓜",
    "category": "瓜果类",
    "price": 3.50,
    "stock": 50,
    "description": "新鲜冬瓜",
    "origin": "广东"
}
```

### 更新蔬菜
- PUT http://localhost:8080/veggie/vegetable/update
- Content-Type: application/json
```json
{
    "id": 1,
    "name": "大白菜",
    "price": 2.80
}
```

### 删除蔬菜
- DELETE http://localhost:8080/veggie/vegetable/delete/{id}
