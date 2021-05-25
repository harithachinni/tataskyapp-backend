package com.cg.apps.tataskyapp.dto;

import java.time.LocalDate;
import java.util.List;

import com.cg.apps.tataskyapp.dto.RechargeToForAcc;
import com.cg.apps.tataskyapp.dto.UserTo;
import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.dto.ServiceRequestTo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountTo {

	private Long accountId;
	private UserTo userTo;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Calcutta")
	private LocalDate registeredDate;
	private List<RechargeToForAcc> rech;

	private List<ServiceRequestTo> requests;
	private Pack currentPack;

	public AccountTo(Account acc) {
		this.accountId = acc.getAccountId();
		this.currentPack = acc.getCurrentPack();
		this.registeredDate = acc.getRegisteredDate();
	}

	public Long getAccountId() {
		return accountId;
	}

	public UserTo getUserTo() {
		return userTo;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public List<ServiceRequestTo> getRequests() {
		return requests;
	}

	public void setRequests(List<ServiceRequestTo> requests) {
		this.requests = requests;
	}

	public Pack getCurrentPack() {
		return currentPack;
	}

	public void setUser(UserTo uto) {
		this.userTo = uto;

	}

	public void setRech(List<RechargeToForAcc> rech) {
		this.rech = rech;
	}

	public List<RechargeToForAcc> getRech() {
		return rech;
	}

}
