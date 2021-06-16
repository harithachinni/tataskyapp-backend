package com.cg.apps.tataskyapp.services;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.exception.RechargeException;
import com.cg.apps.tataskyapp.exception.RequestNotFoundException;
import com.cg.apps.tataskyapp.repository.IAccountRepository;
import com.cg.apps.tataskyapp.repository.IRechargeRepository;
import com.cg.apps.tataskyapp.repository.IServiceRequestRepository;
import com.cg.apps.tataskyapp.services.IServiceRequestService;


@Service
@Transactional
public class IServiceRequestServiceImpl implements IServiceRequestService {

	private Logger logger = LoggerFactory.getLogger(IServiceRequestServiceImpl.class);
	@Autowired
	private IServiceRequestRepository sdao;
	
	
	@Override
	public ServiceRequest createServiceRequestForUser(Account account) {
		logger.info("******** Creating ServiceRequest For User*****");
		ServiceRequest request = new ServiceRequest(account,
				"Service Request is created ");
		return sdao.save(request);
	}
	
	@Override
	public ServiceRequest openedServiceRequest(Account account) {
		logger.info("******** Updating Request **** ");
		ServiceRequest Req = sdao.findOpenedServiceRequest(account);
		
		return sdao.save(Req);
		
	}



	@Override
	public ServiceRequest close(Long serviceRequestId) throws RequestNotFoundException {
		logger.info("******** Closing Request **** ");
		Optional<ServiceRequest> opt = sdao.findById(serviceRequestId);
		if(!opt.isPresent()) {
			throw new RequestNotFoundException("request not found for id: " + serviceRequestId);
		}
		ServiceRequest req = opt.get();
		req.setStatusOpened(false);
	
		sdao.save(req);
		return req;

	}

}
