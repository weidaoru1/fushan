package com.fushan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
//@MapperScan(basePackages = { "com.fushan.mapper" }) //启动时不扫描，也可以在mapper文件注解 @Mapper 把接口变成spring容器中的bean
//@ServletComponentScan(basePackages = {"com.fushan.common.filter"})
public class FushanApplication {

    public static void main(String[] args) {
        SpringApplication.run(FushanApplication.class, args);
    }

}
