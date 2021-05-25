package com.cg.apps.tataskyapp.dto;

import java.util.List;

public class PackDetails {

	private Long id;
	private Double cost;
	private Integer daysValidity;
	private String description;
	private String planName;
	private List<String> channels;

	public PackDetails() {
	}

	public PackDetails(Long id, Double cost, Integer daysValidity, String description, String planName) {
		this.id = id;
		this.cost = cost;
		this.daysValidity = daysValidity;
		this.description = description;
		this.planName = planName;
	}

	public PackDetails(Long id, Double cost, Integer daysValidity, String description, String planName,
			List<String> channels) {
		this.id = id;
		this.cost = cost;
		this.daysValidity = daysValidity;
		this.description = description;
		this.planName = planName;
		this.channels = channels;
	}

	@Override
	public String toString() {
		return "PackDetails [id=" + id + ", cost=" + cost + ", daysValidity=" + daysValidity + ", description="
				+ description + ", planName=" + planName + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
