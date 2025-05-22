package com.warrr.zipflex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ZipflexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipflexApplication.class, args);
    }

}
