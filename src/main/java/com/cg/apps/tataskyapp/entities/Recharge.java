package com.cg.apps.tataskyapp.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;

//import com.cg.apps.tataskyapp.constants.PlanStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "recharge")
public class Recharge {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "acc_id")
	
	private Account account;
	private Double amount;
	private Integer daysValidity;
	private String planDescription;
	private String planName;
	
	@JsonFormat(pattern = "dd-MMM-yyyy", timezone = "Asia/Calcutta")
	private LocalDate purchasedDate;
	@ElementCollection
	private List<String> channels;

	public Recharge() {
		super();
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pack_id")
	private Pack pack;
	private boolean active;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getDaysValidity() {
		return daysValidity;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public void setDaysValidity(Integer daysValidity) {
		this.daysValidity = daysValidity;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}

	@Override
	public String toString() {
		return "Recharge [id=" + id + ", account=" + account + ", amount=" + amount + ", daysValidity=" + daysValidity
				+ ", planDescription=" + planDescription + ", planName=" + planName + ", purchasedDate=" + purchasedDate
				+ ", channels=" + channels + ", pack=" + pack + ", active=" + active + "]";
	}

	public LocalDate getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(LocalDate purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public List<String> getChannels() {
		return channels;
	}

	public void setChannels(List<String> channels) {
		this.channels = channels;
	}

	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Recharge(Pack pack, Account account) {
		super();
		this.account = account;
		this.amount = pack.getCost();
		this.daysValidity = pack.getDaysValidity();
		this.planDescription = pack.getDescription();
		this.planName = pack.getPlanName();
		this.purchasedDate = LocalDate.now();
		this.channels = pack.getChannels();
		this.pack = pack;
		this.active = true;
	}

	public Recharge(Double amount, Integer daysValidity, String planDescription, String planName,
			LocalDate purchasedDate, List<String> channels, boolean active) {
		super();
		this.amount = amount;
		this.daysValidity = daysValidity;
		this.planDescription = planDescription;
		this.planName = planName;
		this.purchasedDate = purchasedDate;
		this.channels = channels;
		this.active = active;
	}

	public Recharge(Recharge recharge) {
		this.id = recharge.getId();
		this.amount = recharge.getAmount();
		this.daysValidity = recharge.getDaysValidity();
		this.planDescription = recharge.getPlanDescription();
		this.planName = recharge.getPlanDescription();
		this.purchasedDate = recharge.getPurchasedDate();
		this.channels = recharge.getChannels();
		this.active = recharge.isActive();
	}

	public void copy(Recharge recharge) {
		this.id = recharge.getId();
		this.amount = recharge.getAmount();
		this.daysValidity = recharge.getDaysValidity();
		this.planDescription = recharge.getPlanDescription();
		this.planName = recharge.getPlanDescription();
		this.purchasedDate = recharge.getPurchasedDate();
		this.channels = recharge.getChannels();
		this.active = recharge.isActive();
	}

}
