package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@EnableSpringDataWebSupport
@SpringBootApplication
public class Fj27Application {

	public static void main(String[] args) {
		SpringApplication.run(Fj27Application.class, args);
	}
}
