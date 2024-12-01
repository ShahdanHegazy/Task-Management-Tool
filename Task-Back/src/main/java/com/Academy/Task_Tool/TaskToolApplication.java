package com.Academy.Task_Tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TaskToolApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskToolApplication.class, args);
	}

}
