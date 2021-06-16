package com.cg.apps.tataskyapp.services;

import java.time.LocalDate;
import java.util.List;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;

public interface IRechargeService {
	
	Recharge createRecharge(Pack pack, Account account);

	

	List<Recharge> findRechargesForUserInDescendingOrderByPurchasedDate(Long accountId);

	int rechargesForUserCount(Long accountId);

	List<Recharge> findAllRechargesInPeriod(LocalDate startDate, LocalDate endDate);

	int countRechargesInPeriod(LocalDate startDate, LocalDate endDate);


	double totalRevenueInPeriod(LocalDate startDate, LocalDate endDate);

	int rechargesCountOnPack(Long id);

	
	String expireIfValidityFinished(Long accountId, Long rechargeId);

	List<Recharge> listall();
}
