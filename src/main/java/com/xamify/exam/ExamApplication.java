package com.xamify.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);

		System.out.println("------------------ XamiFy Exam Application Started -----------------");
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}