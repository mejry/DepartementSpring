package com.example.departementges;

import com.example.departementges.models.Role;
import com.example.departementges.models.User;
import com.example.departementges.repository.RoleRepository;
import com.example.departementges.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class DepartementgesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartementgesApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(UserService userService, RoleRepository roleRepository) {
		return args -> {
			// Check if admin user already exists
			if (userService.getAllUsers().isEmpty()) {
				// Create admin role if not already present
				Role adminRole = roleRepository.findByName("ADMIN");
				if (adminRole == null) {
					adminRole = new Role();
					adminRole.setName("ADMIN");
					roleRepository.save(adminRole);
				}

				// Create admin user
				User adminUser = new User();
				adminUser.setName("Admin");
				adminUser.setPrename("Admin");
				adminUser.setEmail("admin@example.com");
				adminUser.setPassword("adminpassword"); // You should hash the password before saving
				adminUser.setRoles(Collections.singleton(adminRole));

				userService.createUser(adminUser,"ADMIN");
			}
		};
	}
}
