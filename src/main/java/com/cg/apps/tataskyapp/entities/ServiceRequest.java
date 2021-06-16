package com.cg.apps.tataskyapp.entities;

import com.cg.apps.tataskyapp.entities.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class ServiceRequest {

	@Id
	@GeneratedValue
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Calcutta")
	private LocalDate requestedDate;

	private boolean statusOpened;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accountId")
	private Account account;

	private String message;
	
	
	public ServiceRequest(LocalDate requestedDate, Boolean statusOpened, Account account, String message) {
		this.requestedDate = requestedDate;
		this.statusOpened = statusOpened;
		this.account = account;
		this.message = message;
	}

	

	public ServiceRequest() {
	}

	public ServiceRequest(String message) {
		this.message = message;
	}

	public ServiceRequest(Account account, String message) {
		this.account = account;
		this.message = message;
		this.requestedDate = LocalDate.now();
		this.statusOpened = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(LocalDate requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatusOpened() {
		return statusOpened;
	}

	public void setStatusOpened(Boolean statusOpened) {
		this.statusOpened = statusOpened;
	}



	/*public boolean getStatusOpened(boolean b) {
		// TODO Auto-generated method stub
		return false;
	}*/

}
