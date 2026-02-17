package com.rgukt.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskSubmissionUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSubmissionUserApplication.class, args);
	}

}
