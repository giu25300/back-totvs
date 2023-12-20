package br.com.giuliano.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class BackTotvsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackTotvsApplication.class, args);
	}

}
