package com.hearc.agendarc.repository;

import java.util.List;
import java.util.Optional;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.User;

import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<Calendar, Long>{
    
    Optional<Calendar> findById(Long id);

    
    List<Calendar> findByOwner(User owner);

    List<Calendar> findByNameLikeIgnoreCase(String string);


}