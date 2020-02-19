package com.kys.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAsync
public class OpenApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenApiApplication.class, args);
	}

}
