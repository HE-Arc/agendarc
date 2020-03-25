package com.hearc.agendarc;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public String newCalendar(@ModelAttribute ("calendar") Calendar calendar, @AuthenticationPrincipal User user) {
		//User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		calendar.setOwner(user);

		calendarRepository.save(calendar);

		return "redirect:/";
	}
}