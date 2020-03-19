package com.hearc.agendarc.repository;

import com.hearc.agendarc.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<User, Long>{
    User findByName(String name);
}