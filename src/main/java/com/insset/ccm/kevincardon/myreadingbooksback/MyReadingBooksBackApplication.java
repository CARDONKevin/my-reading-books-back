package com.insset.ccm.kevincardon.myreadingbooksback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan("com.insset.ccm.kevincardon.myreadingbooksback.persistence.model")
public class MyReadingBooksBackApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyReadingBooksBackApplication.class, args);
	}

}
