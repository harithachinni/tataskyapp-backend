package com.cg.apps.tataskyapp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pack")
public class Pack {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double cost;
	
	private Integer daysValidity;
	
	private String description;
	
	private String planName;
	
	@ElementCollection
	private List<String> channels = new ArrayList<>();

	public Pack() {
	}

	public Pack(Double cost, Integer daysValidity, String description, String planName, List<String> channels) {
		this.cost = cost;
		this.daysValidity = daysValidity;
		this.description = description;
		this.planName = planName;
		this.channels = channels;
	}

	public Pack(Long packId, Double cost2, Integer daysValidity2, String description2, String planName2,
			List<String> channels2) {
		this.id = packId;
		this.cost = cost2;
		this.daysValidity = daysValidity2;
		this.description = description2;
		this.planName = planName2;
		this.channels = channels2;
	}

	@Override
	public String toString() {
		return "Pack [id=" + id + ", cost=" + cost + ", daysValidity=" + daysValidity + ", description=" + description
				+ ", planName=" + planName + ", channels=" + channels + "]";
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
