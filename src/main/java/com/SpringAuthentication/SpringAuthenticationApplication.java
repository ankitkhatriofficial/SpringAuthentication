package com.SpringAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ankit Khatri
 */

@SpringBootApplication
public class SpringAuthenticationApplication {

	/* Main Function to start the Application */
	public static void main(String[] args) {
		SpringApplication.run(SpringAuthenticationApplication.class, args);
	}

	/* Bean for Password Encoding */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
