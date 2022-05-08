package com.example.dong;

import com.example.dong.netty.BootNettyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

/*
* 引导类 spring boot项目的入口
* */
@Async
@Slf4j
@SpringBootApplication
public class DongApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DongApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception{
        new BootNettyService().bind(5002);
    }
}
