package com.cg.apps.tataskyapp.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "acc")
public class Account {
	@Id
	@GeneratedValue
	private Long accountId;
	
	
	@OneToOne(mappedBy ="account", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Recharge> recharges = new ArrayList<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Calcutta")
	@Column(name = "registered_date")
	private LocalDate registeredDate;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	//@JoinColumn(name="ServiceRequestId")
	private List<ServiceRequest> requests = new ArrayList<>();
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "pck_Id")
	private Pack currentPack;

	public Account() {
		this.registeredDate = LocalDate.now();
	}

	public Account(LocalDate registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Recharge> getRecharges() {
		return recharges;
	}

	public void setRecharges(List<Recharge> recharges) {
		this.recharges = recharges;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDate registeredDate) {
		this.registeredDate = registeredDate;
	}

	public List<ServiceRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<ServiceRequest> requests) {
		this.requests = requests;
	}

	public Pack getCurrentPack() {
		return currentPack;
	}

	public void setCurrentPack(Pack currentPack) {
		this.currentPack = currentPack;
	}

	public Account(Long accountId, User user, List<Recharge> recharges, LocalDate registeredDate,
			List<ServiceRequest> requests, Pack currentPack) {
		this.accountId = accountId;
		this.user = user;
		this.recharges = recharges;
		this.registeredDate = registeredDate;
		this.requests = requests;
		this.currentPack = currentPack;
	}

	public Account(Account account) {
		this.accountId = account.getAccountId();
		this.user = account.getUser();
		this.recharges = account.getRecharges();
		this.registeredDate = account.getRegisteredDate();
		this.requests = account.getRequests();
		this.currentPack = account.getCurrentPack();
	}

	

	public Account(Long accountId, List<Recharge> recharges, LocalDate registeredDate, List<ServiceRequest> requests,
			Pack currentPack) {
		super();
		this.accountId = accountId;
		this.recharges = recharges;
		this.registeredDate = registeredDate;
		this.requests = requests;
		this.currentPack = currentPack;
	}

	
	

	public Account(Long accountId, User user, Pack currentPack) {
		super();
		this.accountId = accountId;
		this.user = user;
		this.currentPack = currentPack;
	}

	public void copy(Account account) {

		this.accountId = account.getAccountId();
		this.user = account.getUser();
		this.recharges = account.getRecharges();
		this.registeredDate = account.getRegisteredDate();
		this.requests = account.getRequests();
		this.currentPack = account.getCurrentPack();

	}

}
