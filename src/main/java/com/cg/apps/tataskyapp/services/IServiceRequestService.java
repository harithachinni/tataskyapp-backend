package com.cg.apps.tataskyapp.services;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.ServiceRequest;

public interface IServiceRequestService {
	
	ServiceRequest createServiceRequestForUser(Account account);

	ServiceRequest openedServiceRequest(Account account);

	ServiceRequest close(Long serviceRequestId);
}
