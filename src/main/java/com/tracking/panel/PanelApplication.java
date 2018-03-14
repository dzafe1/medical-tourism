package com.tracking.panel;

import com.tracking.panel.domain.User;
import com.tracking.panel.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@SpringBootApplication
public class PanelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanelApplication.class, args);
	}
	@Component
	public static class BootstrapBitcoinEvangelists implements ApplicationRunner {

		private UserRepository userRepository;

		public BootstrapBitcoinEvangelists(UserRepository userRepository) {
			this.userRepository = userRepository;
		}

		@Override
		public void run(ApplicationArguments args) {
			BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
			//userRepository.save(new User("Haris", "Dzafic","dzafe1987@gmail.com",bCryptPasswordEncoder.encode("123456"),"images/avatar5.png",true,"ADMIN"));
// userRepository.save(new User(fName, lName, email, bCryptPasswordEncoder.encode(password), imgPath, true, "ADMIN"));
		}
	}
}
