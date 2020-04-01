package com.hearc.agendarc;

import java.security.Principal;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CalendarController{
    
	@Autowired
	CalendarRepository calendarRepository;

	@Autowired
	UserRepository userRepository;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String test() {
        return "calendar";
    }
    
    @RequestMapping(value = "/list", method=RequestMethod.GET)
	public String liste(Model model) {
		model.addAttribute("calendars", calendarRepository.findAll());
		return "main";
	}

	@RequestMapping("/newCalendar")
    public String register(Model model) 
	{
		model.addAttribute("calendar", new Calendar());

    	return "newCalendar";
	}

	@PostMapping("/newCalendar")
	public String newCalendar(@ModelAttribute ("calendar") Calendar calendar, Principal principal) {
		User user = userRepository.findByUsername(principal.getName());
		calendar.setOwner(user);

		calendarRepository.save(calendar);

		return "redirect:/";
	}
}