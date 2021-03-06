package org.bspv.pharma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

@SpringBootApplication(exclude = { FlywayAutoConfiguration.class })
public class PharmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmaApplication.class, args);
	}
}
