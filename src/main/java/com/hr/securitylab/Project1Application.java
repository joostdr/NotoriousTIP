package com.hr.securitylab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * Class which is used for running the spring boot application
 * @EnableAutoConfiguration is used bc of an error > idk
 * ImportResource reads the spring configuration file
 */
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@ImportResource("spring.xml")
public class Project1Application {
	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}
}
