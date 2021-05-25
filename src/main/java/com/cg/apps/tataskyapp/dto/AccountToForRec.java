package com.cg.apps.tataskyapp.dto;

import java.time.LocalDate;
import java.util.List;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.dto.UserToForRech;
import com.cg.apps.tataskyapp.dto.ServiceRequestTo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountToForRec {

	private Long accountId;
	private UserToForRech userTo;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Calcutta")
	private LocalDate registeredDate;
	private List<ServiceRequestTo> requests;
	private Pack currentPack;

	public AccountToForRec(Account acc) {
		this.accountId = acc.getAccountId();
		this.currentPack = acc.getCurrentPack();
		this.registeredDate = acc.getRegisteredDate();
	}

	public Long getAccountId() {
		return accountId;
	}

	public UserToForRech getUserTo() {
		return userTo;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public List<ServiceRequestTo> getRequests() {
		return requests;
	}

	public Pack getCurrentPack() {
		return currentPack;
	}

	public void setUser(UserToForRech uto) {
		this.userTo = uto;

	}

	public void setRequests(List<ServiceRequestTo> requests) {
		this.requests = requests;
	}

}
