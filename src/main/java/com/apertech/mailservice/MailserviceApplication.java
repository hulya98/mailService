package com.apertech.mailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class MailserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailserviceApplication.class, args);
	}

}

