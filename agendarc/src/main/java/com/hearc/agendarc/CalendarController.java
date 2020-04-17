package com.hearc.agendarc;

import java.security.Principal;
import java.util.List;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.Event;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.EventRepository;
import com.hearc.agendarc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CalendarController{
    
	@Autowired
	CalendarRepository calendarRepository;

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailServiceImpl userService;
	
	@Autowired
	CalendarService calendarService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String test() {
        return "home";
    }
    
    @RequestMapping(value = "/calendars", method=RequestMethod.GET)
	public String liste(Model model, @RequestParam(defaultValue="")  String name,@RequestParam(defaultValue="0") int page) {
		Pageable pageable = PageRequest.of(page,1);
		model.addAttribute("calendars", calendarService.findByNameLikeIgnoreCase(name,pageable));
		if(calendarService.findByNameLikeIgnoreCase(name,pageable).getTotalPages()<1)
		{
			int totalPages = 0;
			model.addAttribute("totalPages", totalPages);
		}
		else
		{
			int totalPages = calendarService.findByNameLikeIgnoreCase(name,pageable).getTotalPages()-1;
			model.addAttribute("totalPages", totalPages);
		}
				
		model.addAttribute("currentPage", page);
		return "calendars";
	}

	@RequestMapping(value = "/calendar", method=RequestMethod.GET)
	public String calendar(@RequestParam("id") Long id, Model model) {
		Calendar calendar = calendarRepository.findById(id).get();
		model.addAttribute("calendar", calendar);
		List<Event> events = eventRepository.findByCalendar(calendar);
		model.addAttribute("events", events);
		return "calendar";
	}

	@RequestMapping("/newCalendar")
    public String register(Model model) 
	{
		model.addAttribute("calendar", new Calendar());

    	return "newCalendar";
	}

	@PostMapping("/newCalendar")
	public String newCalendar(Model model,@ModelAttribute ("calendar") Calendar calendar, Principal principal) {

		User user = userRepository.findByUsername(principal.getName());

		calendar.setOwner(user);

		calendarRepository.save(calendar);

		return "redirect:/";
	}

	@RequestMapping(value="/updateCalendar",method=RequestMethod.GET)
	public String update(Model model,@RequestParam("id") Long id)
	{
		Calendar c = calendarRepository.findById(id).get();
		model.addAttribute("calendar", c);
		return "updateCalendar";
	}

	@PostMapping(path="/updateCalendar")
	public String updateCalendar(@ModelAttribute ("calendar") Calendar calendar) {

	calendarRepository.save(calendar);
	return "redirect:/calendars";
	}

	

	@RequestMapping(value="/deleteCal",method=RequestMethod.GET)
	public String deleteEvent(@RequestParam("id") Long id)
	{
		
		Calendar c = calendarRepository.findById(id).get();

		calendarRepository.delete(c);
		return "redirect:/calendars";
	}
}