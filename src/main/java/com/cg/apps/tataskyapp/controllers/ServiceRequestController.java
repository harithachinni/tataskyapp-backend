package com.cg.apps.tataskyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.dto.ServiceRequestTo;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.services.IServiceRequestService;

@RestController
//@RequestMapping is used to map the web requests 
@RequestMapping("/service")
public class ServiceRequestController {

	@Autowired
	private IServiceRequestService serviceReq;

	

	/*
	 * @PostMapping is used to handle POST type of request method
	 * @RequestBody is used to bind HTTP request with an object in a method parameter.
	 * This method adds request for the given account.
	 */
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/addrequest")
	public ServiceRequestTo createServiceRequestForUser(@RequestBody Account account) {
		return new ServiceRequestTo(serviceReq.createServiceRequestForUser(account));

	}

	/*
	 * @GetMapping: It is used to handle GET type of request method.
	 * @PathVariable: It is used to extract the values from the URI.
	 * This method search the request with the given ID.
	 */
	
	
	@GetMapping("/close/{id}")
	public ServiceRequestTo close(@PathVariable("id") Long serviceRequestId) {
		return new ServiceRequestTo(serviceReq.close(serviceRequestId));

	}

	@GetMapping("/opened/requests/{accountId}")
	public ServiceRequestTo openedServiceRequest(@PathVariable("accountId") Long accountId) {
		return new ServiceRequestTo(serviceReq.openedServiceRequest(accountId));

	}

}
