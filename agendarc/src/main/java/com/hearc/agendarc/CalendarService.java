package com.hearc.agendarc;

import java.util.List;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.repository.CalendarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;
    
    public List<Calendar> findByNameLikeIgnoreCase(String name) {
		return  calendarRepository.findByNameLikeIgnoreCase("%"+name+"%");
	}

}

