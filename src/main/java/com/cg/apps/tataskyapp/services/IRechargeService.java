package com.cg.apps.tataskyapp.services;

import java.time.LocalDate;
import java.util.List;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;

public interface IRechargeService {

	Recharge createRecharge(Pack pack, Account account);

	Recharge update(Recharge recharge);

	List<Recharge> findRechargesForUserInDescendingOrderByPurchasedDate(Account account);

	int rechargesForUserCount(Account account);

	List<Recharge> findAllRechargesInPeriod(LocalDate startDate, LocalDate endDate);

	int countRechargesInPeriod(LocalDate startDate, LocalDate endDate);

	/**
	 * calculates revenue by add of all recharges
	 */
	double totalRevenueInPeriod(LocalDate startDate, LocalDate endDate);

	/**
	 *
	 * recharges done on a pack
	 */
	int rechargesCount(Pack pack);

	/**
	 * expire recharge if validity is over, mark active flag as false, also remove
	 * current plan from account
	 */
	Recharge expireIfValidityFinished(Account account, Recharge recharge);

	List<Recharge> listall();
}
