package com.hearc.agendarc.repository;

import com.hearc.agendarc.model.Utilisateur;

import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long>{
    Utilisateur findByName(String name);
}