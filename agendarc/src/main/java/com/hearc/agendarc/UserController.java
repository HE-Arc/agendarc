package com.hearc.agendarc;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.UserRepository;



@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CalendarRepository calendarRepository;

	
	
    @RequestMapping("/profile")
    public String profile(Model model,Principal principal)
    {
		User user = userRepository.findByUsername(principal.getName());

		model.addAttribute("userConnected", user);
		
		List<Calendar> calendars = calendarRepository.findByOwner(user);
		model.addAttribute("number", calendars.size());
		model.addAttribute("calendars", calendars);
		
    	return "profile";
    }

}
