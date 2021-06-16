package com.cg.apps.tataskyapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.dto.ServiceRequestTo;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.exception.RequestNotFoundException;
import com.cg.apps.tataskyapp.repository.IServiceRequestRepository;
import com.cg.apps.tataskyapp.services.IServiceRequestService;
import com.cg.apps.tataskyapp.services.IServiceRequestServiceImpl;

@RestController
//@RequestMapping is used to map the web requests 
@RequestMapping("/service")
@CrossOrigin("*")
public class ServiceRequestController {

	@Autowired
	private IServiceRequestService serviceReq;
	
	/*@Autowired
	private IServiceRequestServiceImpl serviceReqImpl;
	@Autowired
	private IServiceRequestRepository srepo;*/
	

	/*
	 * @PostMapping is used to handle POST type of request method
	 * @RequestBody is used to bind HTTP request with an object in a method parameter.
	 * This method adds request for the given account.
	 */
	
	
	@PostMapping("/addrequest")
	
	//public ServiceRequestTo createServiceRequestForUser(@RequestBody Account account) {
		//return new ServiceRequestTo(serviceReq.createServiceRequestForUser(account));

	//}
	public ResponseEntity<ServiceRequest> createServiceRequestForUser(@Valid @RequestBody Account account){
		ServiceRequest service=serviceReq.createServiceRequestForUser(account);
		ResponseEntity<ServiceRequest> entity=new ResponseEntity<ServiceRequest>(service,HttpStatus.CREATED);
		return entity;
	}
	

	/*
	 * @GetMapping: It is used to handle GET type of request method.
	 * @PathVariable: It is used to extract the values from the URI.
	 * This method search the request with the given ID.
	 */
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/close/{id}")
	public ServiceRequestTo close(@PathVariable("id") Long serviceRequestId) {
		return new ServiceRequestTo(serviceReq.close(serviceRequestId));

	}

	
	/*@PutMapping("/opened/requests/{accountId}")
	public ResponseEntity<ServiceRequest> openedServiceRequest(@PathVariable(value = "accountId") Long accountId,
			  @Valid @RequestBody Account account){
		
			 ServiceRequest request=serviceReq.openedServiceRequest(account);
			 ResponseEntity<ServiceRequest> entity=new ResponseEntity<ServiceRequest>(request,HttpStatus.OK);
			 return entity;
	}



	/*@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/openedrequests/{accountId}")
	public ResponseEntity<ServiceRequest> openedServiceRequest(@RequestBody Account account)
	{
		if(srepo.findOpenedServiceRequest(account).getId()==0)
		{
			throw new RequestNotFoundException("Request not found");
		}
		else {
			return new ResponseEntity<ServiceRequest>(srepo.findOpenedServiceRequest(account),HttpStatus.OK);
		}
	}
	public ServiceRequestTo openedServiceRequest(@RequestBody Account account)
	{
		return new ServiceRequestTo(serviceReq.openedServiceRequest(account));
	}
	
	@GetMapping("/opened/requests")
	public ServiceRequest openedServiceRequest(@RequestBody Account account) {
		return serviceReq.openedServiceRequest(account);
	}*/
	@GetMapping("/update/{accountId}")
    public ResponseEntity<ServiceRequest> openedServiceRequest(@RequestBody Account account,@PathVariable Account accountId) 
    {
		ServiceRequest service=serviceReq.openedServiceRequest(account);
		ResponseEntity<ServiceRequest> entity=new ResponseEntity<ServiceRequest>(service,HttpStatus.OK);
		return entity;	
    }
}