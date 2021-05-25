package com.cg.apps.tataskyapp.dto;

import java.time.LocalDate;
import java.util.List;

import com.cg.apps.tataskyapp.dto.RechargeToForAcc;
import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.dto.ServiceRequestTo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountToUser {

	private Long accountId;
	private List<RechargeToForAcc> recharges;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Calcutta")
	private LocalDate registeredDate;
	private List<ServiceRequestTo> requests;
	private Pack currentPack;

	public AccountToUser(Account acc) {
		this.accountId = acc.getAccountId();
		this.currentPack = acc.getCurrentPack();
		this.registeredDate = acc.getRegisteredDate();
	}

	public Long getAccountId() {
		return accountId;
	}

	public List<RechargeToForAcc> getRecharges() {
		return recharges;
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

	public void setRecharges(List<RechargeToForAcc> recharges) {
		this.recharges = recharges;
	}

	public void setRequests(List<ServiceRequestTo> requests) {
		this.requests = requests;
	}

}
