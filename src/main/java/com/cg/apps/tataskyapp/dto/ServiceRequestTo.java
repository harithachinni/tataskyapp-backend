package com.cg.apps.tataskyapp.dto;

import java.time.LocalDate;

import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ServiceRequestTo {

	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate requestedDate;

	private Boolean statusOpened;

	public Long getId() {
		return id;
	}

	public LocalDate getRequestedDate() {
		return requestedDate;
	}

	public Boolean getStatusOpened() {
		return statusOpened;
	}

	public ServiceRequestTo(ServiceRequest sReq) {
		this.id = sReq.getId();
		this.requestedDate = sReq.getRequestedDate();
		this.statusOpened = sReq.getStatusOpened();
	}

}
