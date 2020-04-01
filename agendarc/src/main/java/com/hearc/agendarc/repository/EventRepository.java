package com.hearc.agendarc.repository;

import java.util.List;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.Event;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long>{

    List<Event> findByCalendar(Calendar calendar);
}