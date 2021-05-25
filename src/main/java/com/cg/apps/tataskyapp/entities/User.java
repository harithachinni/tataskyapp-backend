package com.cg.apps.tataskyapp.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.checkerframework.common.aliasing.qual.Unique;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.validation.annotation.Validated;

import com.cg.apps.tataskyapp.entities.Account;

@Entity
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message="User Name should not be Null")
	@Column(unique=true)
	@Unique
	private String username;
	

	@NotBlank(message="First Name should not be Null")
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@NotBlank(message="Password should not be Null")
	private String password;

	@NotBlank(message="Role should not be Null")
	private String role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "acc_id")
	private Account account;
	
	@NotNull
	private boolean active;
	
	public Long getId() {
		return id;
	}

	public User() {
		
	}

	public User(Long id,String username, String firstName, String lastName, String password, String role, boolean active, Account account) {
		this.id=id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
		this.active=active;
		this.account=account;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User(User user) {
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.account = user.getAccount();
	}

	public void copy(User user) {
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.account = user.getAccount();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

	public User(Long id, String username, String firstName, String lastName, String password, String role, boolean active) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
		this.active=active;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", role=" + role + ", account=" + account.getAccountId() + "]";
	}
	
	public User(String username, String firstName, String lastName, String password, String role) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}

	public User(@Unique String username, String firstName, String lastName, String password, String role, boolean active) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
		this.active = active;
	}

	public User(Long id, @Unique String username, String firstName, String lastName, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}

	
	
	
	
}