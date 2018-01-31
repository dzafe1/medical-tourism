package com.tracking.panel;

import com.tracking.panel.domain.User;
import com.tracking.panel.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PanelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanelApplication.class, args);
	}

}
