package com.xt.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @MapperScan ：扫描 mapper 包下的 Mapper 接口
 */
@MapperScan("com.xt.mp.mapper")
@SpringBootApplication
public class MpSpringbootTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpSpringbootTestApplication.class, args);
    }

}
