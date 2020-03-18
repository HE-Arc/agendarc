package com.hearc.agendarc.repository;

import com.hearc.agendarc.models.Calendar;

import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<Calendar, Long>{
}