package com.hearc.agendarc;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hearc.agendarc.model.Role;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.RoleRepository;
import com.hearc.agendarc.repository.UserRepository;



@Controller
public class RegisterController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	RoleRepository roleRepo;

	
    @RequestMapping("/register")
    public String register(Model model) 
	{
		model.addAttribute("user", new User());

    	return "register";
	}
    
	@PostMapping("/add")
	public String add(@ModelAttribute ("user") User user) {

//		user.setName(user.getName());
//		user.setSurname(user.getSurname());
//		user.setUsername(user.getUsername());
		user.setPwd((bCryptPasswordEncoder.encode(user.getPwd())));
		System.out.println(user.getName());
		System.out.println(user.getSurname());
		System.out.println(user.getPwd());
		System.out.println(user.getUsername());
		
		/*
		final Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepo.save(roleUser);
		
		final Set<Role> roles = new HashSet<>();
		  roles.add(roleUser);
		  user.setRoles(roles);
		  */

		
		userRepo.save(user);

	
		ArrayList<User> users = (ArrayList<User>) userRepo.findAll();
		
		return "redirect:/";
	}
	
}
