package com.example.bmwdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
//@EnableJpaRepositories("com.example.bmwdemo.repository")
//@EntityScan("com.example.bmwdemo.dto")
public class BmwDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmwDemoApplication.class, args);
    }

}
