package com.cg.apps.tataskyapp.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AddRequest {

	public AddRequest() {
	}

	private Long packId;
	private Double cost;
	private Integer daysValidity;
	private String description;
	private String planName;
	private List<String> channels = new ArrayList<>();
	private Long accountId;
	private User user;
	private List<Recharge> recharges = new ArrayList<>();
	@JsonFormat(pattern = "dd-MMM-yyyy", timezone = "Asia/Calcutta")
	private LocalDate registeredDate;
	private List<ServiceRequest> requests = new ArrayList<>();
	private Pack currentPack;

	public AddRequest(Long packId, Double cost, Integer daysValidity, String description, String planName,
			List<String> channels, Long accountId, User user, List<Recharge> recharges, LocalDate registeredDate,
			List<ServiceRequest> requests, Pack currentPack) {
		super();
		this.packId = packId;
		this.cost = cost;
		this.daysValidity = daysValidity;
		this.description = description;
		this.planName = planName;
		this.channels = channels;
		this.accountId = accountId;
		this.user = user;
		this.recharges = recharges;
		this.registeredDate = registeredDate;
		this.requests = requests;
		this.currentPack = currentPack;
	}

	public Long getPackId() {
		return packId;
	}

	public Double getCost() {
		return cost;
	}

	public Integer getDaysValidity() {
		return daysValidity;
	}

	public String getDescription() {
		return description;
	}

	public String getPlanName() {
		return planName;
	}

	public List<String> getChannels() {
		return channels;
	}

	public Long getAccountId() {
		return accountId;
	}

	public User getUser() {
		return user;
	}

	public List<Recharge> getRecharges() {
		return recharges;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public List<ServiceRequest> getRequests() {
		return requests;
	}

	public Pack getCurrentPack() {
		return currentPack;
	}

}
