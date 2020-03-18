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
import com.hearc.agendarc.model.Utilisateur;
import com.hearc.agendarc.repository.RoleRepository;
import com.hearc.agendarc.repository.UtilisateurRepository;

@SpringBootApplication
public class AgendarcApplication {

	public static void main(String[] args) {
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
	UtilisateurRepository userRepo;


	@PostConstruct
	public void initData() {
	  //Création du role admin
	  Role roleAdmin = new Role();
	  roleAdmin.setName("ROLE_ADMIN"); 
	  roleRepo.save(roleAdmin);
	  
	  //creation de l'utilisateur
	  Utilisateur u = new Utilisateur();
	  u.setName("admin");
	  u.setPwd((bCryptPasswordEncoder.encode("password")));
	  	  
	  //Ajout des rôles à l'utilisateur
	  Set<Role> roles = new HashSet<>();
	  roles.add(roleAdmin);
	  u.setRoles(roles);
	  userRepo.save(u);
	  
	}
}
