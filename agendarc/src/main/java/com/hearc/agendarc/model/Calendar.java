package com.hearc.agendarc.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Utilisateur owner;

    @ManyToMany
    private Set<Utilisateur> users;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Utilisateur getOwner()
    {
        return owner;
    }

    public void setOwner(Utilisateur owner)
    {
        this.owner = owner;
    }

    public Set<Utilisateur> getUsers()
    {
        return users;
    }

    public void setUsers(Set<Utilisateur> users)
    {
        this.users = users;
    }
    

}