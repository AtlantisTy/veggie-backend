package com.veggie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.veggie.mapper")
public class VeggieApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeggieApplication.class, args);
        System.out.println("========================================");
        System.out.println("= Veggie Backend 启动成功!              =");
        System.out.println("= 访问地址: http://localhost:8080       =");
        System.out.println("= API文档: http://localhost:8080/veggie =");
        System.out.println("========================================");
    }
}
