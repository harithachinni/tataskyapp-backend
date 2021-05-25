package com.cg.apps.tataskyapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.exception.RequestNotFoundException;
import com.cg.apps.tataskyapp.repository.IServiceRequestRepository;
import com.cg.apps.tataskyapp.services.IServiceRequestService;


@Service
@Transactional
public class IServiceRequestServiceImpl implements IServiceRequestService {

	@Autowired
	private IServiceRequestRepository sdao;

	@Override
	public ServiceRequest createServiceRequestForUser(Account account) {
		ServiceRequest request = new ServiceRequest(account,
				"Recharge Pending... Kindly Recharge to Continue Watching.. ");
		return sdao.save(request);
	}

	@Override
	public ServiceRequest openedServiceRequest(Long accountId) {
		return sdao.findOpenedServiceRequest(accountId);
	}

	@Override
	public ServiceRequest close(Long serviceRequestId) throws RequestNotFoundException {
		Optional<ServiceRequest> opt = sdao.findById(serviceRequestId);
		if (!opt.isPresent()) {
			throw new RequestNotFoundException("request not found for id: " + serviceRequestId);
		}
		ServiceRequest req = opt.get();
		req.setStatusOpened(false);
		sdao.save(req);
		return req;

	}

}
