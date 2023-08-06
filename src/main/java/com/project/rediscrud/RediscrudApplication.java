package com.project.rediscrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class RediscrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RediscrudApplication.class, args);
    }

}
