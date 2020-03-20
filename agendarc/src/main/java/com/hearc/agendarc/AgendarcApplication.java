package com.hearc.agendarc;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hearc.agendarc.model.Role;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.RoleRepository;
import com.hearc.agendarc.repository.UserRepository;

@SpringBootApplication
public class AgendarcApplication {

	public static void main(final String[] args) {
		SpringApplication.run(AgendarcApplication.class, args);
	}

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	UserRepository userRepo;

	@PostConstruct
	public void initData() {
		// Création du role admin
		final Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		
		final Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepo.save(roleUser);
		roleRepo.save(roleAdmin);

		

		// creation de l'utilisateur
		final User u = new User();
		final User u2=new User();
		
		u.setName("admin");
		u.setSurname("admin");
		u.setUsername("admin");
		u.setPwd((bCryptPasswordEncoder.encode("password")));
		
		u2.setName("test");
		u2.setSurname("test");
		u2.setUsername("test");
		u2.setPwd((bCryptPasswordEncoder.encode("test1234")));

		// Ajout des rôles à l'utilisateur
		final Set<Role> roles = new HashSet<>();
	  roles.add(roleAdmin);
	  u.setRoles(roles);
	  u2.setRoles(roles);
	  userRepo.save(u);
	  userRepo.save(u2);
	  
	}
}
