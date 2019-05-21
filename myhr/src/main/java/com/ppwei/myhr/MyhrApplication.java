package com.ppwei.myhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ppwei.myhr.mapper")
public class MyhrApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyhrApplication.class, args);
    }

}
