package com.hearc.agendarc;

import javax.annotation.PostConstruct;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Autowired
    UserRepository userRepository;
    
	@Autowired
	CalendarRepository calendarRepository;
	
	@PostConstruct
	public void init() {
        User u = new User();
        u.setName("Dave");
        u.setSurname("Silva");
        u.setUsername("dave");
        u.setPwd("test");
        userRepository.save(u);

        Calendar c = new Calendar();
        c.setName("Euro 2020");
        c.setOwner(u);
        calendarRepository.save(c);

        Calendar c1 = new Calendar();
        c1.setName("JO Tokyo");
        c1.setOwner(u);
        calendarRepository.save(c1);
	}
}
