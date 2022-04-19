package com.mr.mymap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMapApplication.class, args);
	}

}
