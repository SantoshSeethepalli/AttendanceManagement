package com.santosh.SpringSecurity;

import com.santosh.SpringSecurity.Application.DataTransferObjects.StudentResponseDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringSecurityApplication.class, args);
	}
}
