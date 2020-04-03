package com.hearc.agendarc;

import java.time.LocalDate;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.Event;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.EventRepository;
import com.hearc.agendarc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {
	
	@Autowired
    UserRepository userRepository;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Autowired
    CalendarRepository calendarRepository;
    
        
	@Autowired
	EventRepository eventRepository;
	
	@PostConstruct
	public void init() {
        final User u = new User();
        u.setName("dave");
        u.setSurname("Silva");
        u.setUsername("dave");
        u.setPwd((bCryptPasswordEncoder.encode("test")));
        userRepository.save(u);

        final Calendar c = new Calendar();
        c.setName("Euro 2020");
        c.setOwner(u);
        calendarRepository.save(c);

        final Calendar c1 = new Calendar();
        c1.setName("JO Tokyo");
        c1.setOwner(u);
        calendarRepository.save(c1);

    
        LocalDate localDate = LocalDate.now();

        final Event e1 = new Event();
        e1.setName("Suisse-Italie");
        e1.setDate(localDate);
        e1.setCalendar(c);
        e1.setDetails("Match des 8emes de finale");
        eventRepository.save(e1);
        
        final Event e2 = new Event();
        e2.setName("France-Allemagne");
        e2.setDate(localDate);
        e2.setCalendar(c);
        e2.setDetails("Match de poule");
        eventRepository.save(e2);

        final Event e3 = new Event();
        e3.setName("Serbie-Albanie");
        e3.setDate(localDate);
        e3.setCalendar(c);
        e3.setDetails("Match de catch");
        eventRepository.save(e3);

        final Event e4 = new Event();
        e4.setName("Federer-Nadal");
        e4.setDate(localDate);
        e4.setCalendar(c1);
        e4.setDetails("Match pour la médaille d\'or ");
        eventRepository.save(e4);

        final Event e5 = new Event();
        e5.setName("Djokovic-Stanimal");
        e5.setDate(localDate);
        e5.setCalendar(c1);
        e5.setDetails("Match pour la médaille d\'argent");
        eventRepository.save(e5);

        
        
	}
}
