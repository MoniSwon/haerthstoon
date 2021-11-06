package com.mds.back.haerthstoon.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	public enum Role {Deny, Player, Admin};
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	public String name;
	public String email;
	public String hash;
	
	@Enumerated
	public Role role = Role.Deny;
	public int xp=0;
	
	protected User() {}
	
	public User(String name, String email, Role role) {
		this.name = name;
		this.email = email;
		this.role = role;
		this.hash = hash;
	}
	
	public Long getId() {
		return id;
	}
}
