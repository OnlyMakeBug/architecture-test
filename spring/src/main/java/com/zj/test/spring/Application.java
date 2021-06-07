package com.zj.test.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/* @author: zhoujian
 * @create-time: 2020/9/24 12:25
 * @description:
 * @version: 1.0
 */
@SpringBootApplication
/**
 * 注意：开启@Scheduled注解扫描，否则不扫描
 */
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
