package ru.saynurdinov.demo.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@SpringBootApplication
@EnableMethodSecurity
@ComponentScan
public class ForumServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(ForumServiceApplication.class, args);
	}
}
