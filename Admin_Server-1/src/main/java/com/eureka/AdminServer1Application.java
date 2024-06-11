package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class AdminServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminServer1Application.class, args);
	}

}
