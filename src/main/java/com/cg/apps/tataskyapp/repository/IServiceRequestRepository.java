package com.cg.apps.tataskyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.ServiceRequest;

@Repository
public interface IServiceRequestRepository extends JpaRepository<ServiceRequest, Long>{

	@Query("FROM ServiceRequest WHERE statusOpened = 'true' and account =:account")
	ServiceRequest findOpenedServiceRequest(@Param("account") Account account);
	
	@Query("FROM ServiceRequest WHERE statusOpened = 'true' and accountId =:accountId")
	ServiceRequest findOpenedServiceRequest(@Param("accountId") Long accountId);

	/*@Query(value="select add(sr.id) FROM ServiceRequest WHERE statusOpened='true' and account=:account")
	ServiceRequest add(@Param("account")Account account);
	
	ServiceRequest add(ServiceRequest request);*/
}
