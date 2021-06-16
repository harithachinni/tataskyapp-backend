package com.cg.apps.tataskyapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.entities.User;
import com.cg.apps.tataskyapp.repository.IServiceRequestRepository;
import com.cg.apps.tataskyapp.services.IServiceRequestService;
import com.cg.apps.tataskyapp.services.IServiceRequestServiceImpl;


@SpringBootTest
public class ServiceRequestTest {
	
	@Autowired
	private IServiceRequestServiceImpl sreq;
	@MockBean
	private  IServiceRequestRepository srepo;
	Account account;
	User user1;
	Long accountId,serviceRequestId;
	Pack p1;
	ServiceRequest request;
	
	@Test
	public void testcreateServiceRequestForUser() {
		
		Optional<ServiceRequest> s1=Optional.of(new ServiceRequest(LocalDate.now(),true,account,"created" ));
		when(srepo.save(s1.get())).thenReturn(s1.get());
		when(srepo.findById(s1.get().getId())).thenReturn(s1);
		assertEquals(s1.get().getAccount(),sreq.createServiceRequestForUser(account));
	}
	
	@Test
	public void testopenedServiceRequest() {
		
		Account a1 = new Account((long) 1, user1, null, LocalDate.now(), null, p1);
		//when(srepo.save(a1)).thenReturn(a1);
		when(srepo.existsById(a1.getAccountId())).thenReturn(true);
	//	sreq.openedServiceRequest((long) 1);
		//verify(srepo, times(1)).findOpenedServiceRequest((long) 1);
	}
		
	@Test
	public void testclose() {
		
		Optional<ServiceRequest> s1=Optional.of(new ServiceRequest(LocalDate.now(),true,account,"created" ));
		when(srepo.save(s1.get())).thenReturn(s1.get());
		when(srepo.findById(s1.get().getId())).thenReturn(s1);
		sreq.close(serviceRequestId);
		verify(srepo, times(1)).findById(serviceRequestId);
		
	}

}
