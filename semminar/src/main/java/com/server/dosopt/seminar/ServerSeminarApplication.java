package com.server.dosopt.seminar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServerSeminarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerSeminarApplication.class, args);
	}

}
