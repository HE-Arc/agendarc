package com.hearc.agendarc;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hearc.agendarc.repository.UserRepository;



@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	
	
    @RequestMapping("/profile")
    public String profile(Model model,Principal principal)
    {
		model.addAttribute("userConnected", userRepository.findByUsername(principal.getName()));

    	return "profile";
    }

}