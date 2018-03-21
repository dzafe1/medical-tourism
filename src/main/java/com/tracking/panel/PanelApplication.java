package com.tracking.panel;

import com.tracking.panel.domain.Hospital;
import com.tracking.panel.domain.HospitalEmployee;
import com.tracking.panel.domain.HospitalsImages;
import com.tracking.panel.domain.User;
import com.tracking.panel.repository.HospitalEmployeeRepository;
import com.tracking.panel.repository.HospitalRepository;
import com.tracking.panel.repository.HospitalsImagesRepository;
import com.tracking.panel.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@SpringBootApplication
public class PanelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanelApplication.class, args);
	}
	@Component
	public static class MedicisAdminPanel implements ApplicationRunner {

		private UserRepository userRepository;
		private HospitalRepository hospitalRepository;
		private HospitalEmployeeRepository hospitalEmployeeRepository;
		private HospitalsImagesRepository hospitalsImagesRepository;

		public MedicisAdminPanel(UserRepository userRepository,HospitalRepository hospitalRepository,
                                           HospitalEmployeeRepository hospitalEmployeeRepository,HospitalsImagesRepository hospitalsImagesRepository) {
		    this.userRepository = userRepository;
		    this.hospitalRepository = hospitalRepository;
		    this.hospitalEmployeeRepository = hospitalEmployeeRepository;
		    this.hospitalsImagesRepository = hospitalsImagesRepository;
		}

		@Override
		public void run(ApplicationArguments args) {
			BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
			userRepository.save(new User("Haris", "Dzafic","dzafe1987@gmail.com",bCryptPasswordEncoder.encode("123456"),"images/avatar5.png",true,"ADMIN"));
            userRepository.save(new User("Kerim", "Balic","kera@k.com",bCryptPasswordEncoder.encode("123456"),"images/avatar5.png",true,"USER"));
            Hospital hospital=new Hospital("Kosevo","Sarajevo","71000","Kosevska 21","Lorem ipsum dolor sit amet..");
			hospitalRepository.save(hospital);

			hospitalEmployeeRepository.save(new HospitalEmployee("Kerim","Balic","kera@k.com","/images/hospitals employees/loading.png","Doctor",new Date(),true,hospital));
			hospitalEmployeeRepository.save(new HospitalEmployee("Adnan","Mehonic","ado@a.com","/images/hospitals employees/loading.png","Doctor",new Date(),true,hospital));
			hospitalEmployeeRepository.save(new HospitalEmployee("Nejra","Grabovica","nejra@n.com","/images/hospitals employees/loading.png","Doctor",new Date(),true,hospital));
			hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals employees/loading.png",hospital));
            hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals3.jpg",hospital));
            hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals4.jpg",hospital));
            hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals5.jpg",hospital));
            hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals6.jpg",hospital));
            //userRepository.save(new User(fName, lName, email, bCryptPasswordEncoder.encode(password), imgPath, true, "ADMIN"));
		}
	}
}
