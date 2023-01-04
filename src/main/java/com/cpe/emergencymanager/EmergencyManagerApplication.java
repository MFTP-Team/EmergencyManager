package com.cpe.emergencymanager;

import com.cpe.emergencymanager.config.SpringFoxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackageClasses = {EmergencyManagerApplication.class, SpringFoxConfig.class})
public class EmergencyManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmergencyManagerApplication.class, args);
	}

}
