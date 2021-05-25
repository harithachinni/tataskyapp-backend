package com.cg.apps.tataskyapp.dto;

import java.time.LocalDate;
import java.util.List;

import com.cg.apps.tataskyapp.dto.AccountToForRec;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RechargeTo {

	private Long id;
	private AccountToForRec account;
	private Double amount;
	private Integer daysValidity;
	private String planDescription;
	private String planName;
	@JsonFormat(pattern = "dd-MMM-yyyy", timezone = "Asia/Calcutta")
	private LocalDate purchasedDate;
	private List<String> channels;
	private boolean active;

	public Long getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}

	public AccountToForRec getAccount() {
		return account;
	}

	public Double getAmount() {
		return amount;
	}

	public Integer getDaysValidity() {
		return daysValidity;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public String getPlanName() {
		return planName;
	}

	public LocalDate getPurchasedDate() {
		return purchasedDate;
	}

	public List<String> getChannels() {
		return channels;
	}

	public RechargeTo(Recharge rech) {
		this.id = rech.getId();
		this.amount = rech.getAmount();
		this.daysValidity = rech.getDaysValidity();
		this.planDescription = rech.getPlanDescription();
		this.planName = rech.getPlanName();
		this.purchasedDate = rech.getPurchasedDate();
		this.channels = rech.getChannels();
		this.active = rech.isActive();
	}

	public void setAccount(AccountToForRec account) {
		this.account = account;
	}

}
