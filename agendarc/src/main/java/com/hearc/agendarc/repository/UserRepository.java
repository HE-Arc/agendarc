package com.hearc.agendarc.repository;

import com.hearc.agendarc.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByName(String name);
}