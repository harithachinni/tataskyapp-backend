package com.cg.apps.tataskyapp.services;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.services.IAccountService;
import com.cg.apps.tataskyapp.exception.RechargeException;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.repository.IRechargeRepository;

@Service
@Transactional
public class IRechargeServiceImpl implements IRechargeService {

	private Logger logger = LoggerFactory.getLogger(IRechargeServiceImpl.class);

	@Autowired
	private IRechargeRepository rDao;
	@Autowired
	private IAccountService aService;

	@Override
	public Recharge createRecharge(Pack pack, Account account) {
		logger.info("******** Creating Recharge*****");
		Recharge newRecharge = new Recharge(pack, account);
		Recharge rech = rDao.save(newRecharge);
		if (rech != null) {
			Account acc = new Account(account);
			acc.setCurrentPack(pack);
			aService.update(acc);
			rech.getAccount().setCurrentPack(pack);
		}
		return rech;
	}

	@Override
	public Recharge update(Recharge recharge) {
		logger.info("******** Updating Recharge **** ");
		Recharge currRech = rDao.findById(recharge.getId());
		if (currRech == null) {
			throw new RechargeException("Can't find Recharge");
		}
		currRech.copy(recharge);
		return rDao.save(currRech);
	}

	@Override
	public List<Recharge> findRechargesForUserInDescendingOrderByPurchasedDate(Account account) {
		logger.info("******** finding RechargesForUserInDescendingOrderByPurchasedDate  **** ");
		List<Recharge> rech = rDao.findRechargesForUserInDescendingOrderByPurchasedDate(account);
		if (rech.isEmpty()) {
			throw new RechargeException("No recharges Present");
		}
		return rech;
	}

	@Override
	public int rechargesForUserCount(Account account) {
		logger.info("******** rechargesForUserCount*****");
		return rDao.rechargesForUserCount(account);
	}

	@Override
	public List<Recharge> findAllRechargesInPeriod(LocalDate startDate, LocalDate endDate) {
		logger.info("******** finding AllRechargesInPeriod  **** ");
		List<Recharge> rech = rDao.findAllRechargesInPeriod(startDate, endDate);
		if (rech.isEmpty()) {
			throw new RechargeException("No recharges Present");
		}
		return rech;
	}

	@Override
	public int countRechargesInPeriod(LocalDate startDate, LocalDate endDate) {
		logger.info("******** countRechargesInPeriod  **** ");
		return rDao.countRechargesInPeriod(startDate, endDate);
	}

	@Override
	public double totalRevenueInPeriod(LocalDate startDate, LocalDate endDate) {
		logger.info("******** totalRevenueInPeriod  **** ");
		return rDao.totalRevenueInPeriod(startDate, endDate);
	}

	@Override
	public int rechargesCount(Pack pack) {
		logger.info("******** count Recharges **** ");
		return rDao.rechargesCount(pack);
	}

	@Override
	public Recharge expireIfValidityFinished(Account account, Recharge recharge) {
		logger.info("******** Running expireIfValidityFinished**** ");
		Recharge currRech = rDao.findById(recharge.getId());
		LocalDate rechPurDate = currRech.getPurchasedDate();
		int rechValidity = currRech.getDaysValidity();
		if (LocalDate.now().compareTo(rechPurDate) > rechValidity) {
			currRech.setActive(false);
			currRech = update(currRech);
		}
		return currRech;
	}

	public List<Recharge> listall() {
		logger.info("******** finding AllRecharges **** ");
		List<Recharge> rechList = rDao.findAll();
		if (rechList.isEmpty()) {
			throw new RechargeException("No recharges Present");
		}
		return rechList;
	}

}
