package com.hearc.agendarc.repository;

import com.hearc.agendarc.model.Event;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long>{
}