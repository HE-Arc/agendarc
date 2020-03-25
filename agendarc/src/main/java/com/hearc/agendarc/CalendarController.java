package com.hearc.agendarc;

import com.hearc.agendarc.repository.CalendarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
}