package com.hearc.agendarc.repository;

import com.hearc.agendarc.model.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);

	List<User> findByUsernameLike(String string);
    
}