package com.github.prgrms;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "JdbcUserRepository")

public class PrgrmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(PrgrmsApplication.class, args);
	}
}
