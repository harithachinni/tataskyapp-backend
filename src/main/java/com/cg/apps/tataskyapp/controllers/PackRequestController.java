package com.cg.apps.tataskyapp.controllers;

import java.util.List;

public class PackRequestController {

	private Double cost;
	private Integer daysValidity;
	private String description;
	private String planName;
	private List<String> channels;

	@Override
	public String toString() {
		return "CreatePackRequest [cost=" + cost + ", daysValidity=" + daysValidity + ", description=" + description
				+ ", planName=" + planName + "]";
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getDaysValidity() {
		return daysValidity;
	}

	public void setDaysValidity(Integer daysValidity) {
		this.daysValidity = daysValidity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public List<String> getChannels() {
		return channels;
	}

	public void setChannels(List<String> channels) {
		this.channels = channels;
	}

}
