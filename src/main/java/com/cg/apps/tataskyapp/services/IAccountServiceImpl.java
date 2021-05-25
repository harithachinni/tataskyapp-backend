package com.cg.apps.tataskyapp.services;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.repository.IAccountRepository;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.services.IAccountServiceImpl;
import ch.qos.logback.classic.Logger;


@Service
public class IAccountServiceImpl implements IAccountService {
	@Autowired
	IAccountRepository aDao;
	private Logger logger = (Logger) LoggerFactory.getLogger(IAccountServiceImpl.class);

	@Override
	public Account add(Account account) {
		logger.info("******** Account Created*****");
		Account acc = new Account(account);
		aDao.save(acc);
		return acc;
	}

	@Override
	public Account findById(Long accountId) {
		logger.info("******** Account found for id *******" + accountId);
		Optional<Account> opt = aDao.findById(accountId);
//		if(!opt.isPresent()) {
//		throw new AccountNotFoundException("Account not found for id: "+accountId);
//	}
		Account acc = opt.get();
		return acc;
	}

	public Account update(Account account) {
		logger.info("******** Updating Account**** ");
		Optional<Account> opt = aDao.findById(account.getAccountId());
		Account acc = opt.get();
		System.out.println("acc in aService + " + acc);
		acc.copy(account);
		return aDao.save(acc);
	}

	@Override
	public void deleteByAccountId(Long accountId) {
		logger.info("******** Account Deleted for id *******" + accountId);
		aDao.deleteById(accountId);

	}

	@Override
	public int countCreatedAccountsInPeriod(LocalDate startDate, LocalDate endDate) {
		logger.info("******** countAccountsInPeriod  **** ");
		int c = aDao.countCreatedAccountsInPeriod(startDate, endDate);
		return c;
	}

	@Override
	public int countAccounts() {
		logger.info("******** countAccounts **** ");
		int count = aDao.countAccounts();
		return count;
	}

	@Override
	public void removePackForAccount(Account account, Pack pack) {
		Account acc = new Account(account);
		acc.setCurrentPack(null);
		update(acc);
	}

//	@Override
//	public int countCreatedAccounts(LocalDate startDate, LocalDate endDate) {
//		int c=aDao.countCreatedAccounts(startDate, endDate);
//		final int a=c;
//		return a;
//	}

}


	