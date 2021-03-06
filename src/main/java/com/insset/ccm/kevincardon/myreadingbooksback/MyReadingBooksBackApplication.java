package com.insset.ccm.kevincardon.myreadingbooksback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableMongoRepositories(basePackages = "com.insset.ccm.kevincardon.myreadingbooksback.repositories")
public class MyReadingBooksBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyReadingBooksBackApplication.class, args);
    }

}
