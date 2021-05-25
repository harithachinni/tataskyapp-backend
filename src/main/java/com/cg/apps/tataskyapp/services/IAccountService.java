package com.cg.apps.tataskyapp.services;

import java.time.LocalDate;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.exception.AccountNotFoundException;
import com.cg.apps.tataskyapp.entities.Pack;

public interface IAccountService {
	
	Account add(Account account);

	Account findById(Long accountId) throws AccountNotFoundException;

	Account update(Account account);

	void deleteByAccountId(Long accountId);

	int countCreatedAccountsInPeriod(LocalDate startDate, LocalDate endDate);

	/**
	 * finds count of accounts in the application
	 */
	int countAccounts();

	void removePackForAccount(Account account, Pack pack);
}
