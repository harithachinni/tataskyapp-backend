package com.cg.apps.tataskyapp.dto;

import com.cg.apps.tataskyapp.entities.User;

public class UserToForRech {
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String role;

	public UserToForRech(User usr) {
		this.id = usr.getId();
		this.username = usr.getUsername();
		this.firstName = usr.getFirstName();
		this.lastName = usr.getLastName();
		this.role = usr.getRole();
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getRole() {
		return role;
	}

}
