package com.hearc.agendarc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.RoleRepository;
import com.hearc.agendarc.repository.UserRepository;



@Controller
public class RegisterController {
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
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

		return "redirect:/";
	}
	
}
