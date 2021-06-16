package com.cg.apps.tataskyapp.services;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.apps.tataskyapp.dto.AccountToForRec;
import com.cg.apps.tataskyapp.dto.RechargeTo;
import com.cg.apps.tataskyapp.entities.Account;

import com.cg.apps.tataskyapp.exception.RechargeException;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.repository.IAccountRepository;
import com.cg.apps.tataskyapp.repository.IPackRepository;
import com.cg.apps.tataskyapp.repository.IRechargeRepository;

@Service
/*
 * These class files are used to write business logic .
 * 
 */

public class IRechargeServiceImpl implements IRechargeService {

	private Logger logger = (Logger)LoggerFactory.getLogger(IRechargeServiceImpl.class);

	@Autowired
       private IRechargeRepository rechargeRepository;
	@Autowired
    private IPackRepository prepo;
	@Autowired
    private IAccountRepository arepo;
	@Autowired
	private IAccountService aService;
	/*
	 * This method is used to create recharges for user.
	 * 
	 */
	
	
//	@Override
//	public Recharge createRecharge(Long packId,Long accountId) {
//		logger.info("******** Creating Recharge*****");
//			LocalDate date=LocalDate.now();
//    
//		Pack pack=prepo.findById(packId).orElse(null);
//		Account account=arepo.findById(accountId).orElse(null);
//		
//		if (account==null) {
//			throw new RechargeException("The account or pack does not exits");
//		}
//		
//		
//		Recharge recharge= new Recharge();
//		recharge.setAccount(account);
//		recharge.setPack(pack);
//	    recharge.setAmount(pack.getCost());
//		recharge.setDaysValidity(pack.getDaysValidity());
//		recharge.setPlanDescription(pack.getDescription());
//		recharge.setPlanName(pack.getPlanName());
//		recharge.setPurchasedDate(date);
//		List<String> list=new ArrayList<String>();
//		list=pack.getChannels();
//		recharge.setChannels(list);
//		recharge.setActive(true);
//		
//    return rechargeRepository.save(recharge); 
//		
//	}
	@Override
	public Recharge createRecharge(Pack pack, Account account) {
		logger.info("******** Creating Recharge*****");

		if (account==null || pack==null) {
			throw new RechargeException("The account or pack does not exits");
	}
	
		Recharge recharge= new Recharge();
		LocalDate date=LocalDate.now();
		 recharge.setAccount(account);
		recharge.setPack(pack);
	    recharge.setAmount(pack.getCost());
		recharge.setDaysValidity(pack.getDaysValidity());
		recharge.setPlanDescription(pack.getDescription());
		recharge.setPlanName(pack.getPlanName());
		recharge.setPurchasedDate(date);

		recharge.setActive(true);
		
			
		return rechargeRepository.save(recharge);
	}
	/*
	    * It takes in parameters from account by taking in the Id  
	    *  find all the recharges done by that user and sort it in descending order. 
	    */
    
    @Override
	public List<Recharge> findRechargesForUserInDescendingOrderByPurchasedDate(Long accountId) {
	
		logger.info("******** finding RechargesForUserInDescendingOrderByPurchasedDate  **** ");
		Account account=arepo.findById(accountId).orElse(null);
		
		if (account==null) {
			throw new RechargeException("The accountId does not exits");
		}
		List<Recharge> rlist=rechargeRepository.findAll();
		
	       
	
		rlist=rlist.stream().filter(item ->
			item.getAccount().getAccountId()==accountId).collect(Collectors.toList());
	
		Collections.sort(rlist, new Comparator<Recharge>() {
		            public int compare(Recharge o1, Recharge o2) {
		             
		             
		                	return ~(o1.getPurchasedDate().compareTo(o2.getPurchasedDate())) ;
		           
		                
		        }
		    });
		
		return rlist;
		
	}
    /*
	 * it takes in accountId as a parameter and returns the list of all
	 * the recharges done .
	 */

	@Override
	public int rechargesForUserCount(Long accountId) {
		logger.info("******** rechargesForUserCount*****");
Account account=arepo.findById(accountId).orElse(null);
		
		if (account==null) {
			throw new RechargeException("The accountId does not exits");
		}
		List<Recharge> rlist=new ArrayList<Recharge>();
		rlist=rechargeRepository.findAll();
		
		rlist=rlist.stream().filter(item ->
			item.getAccount().getAccountId()==accountId).collect(Collectors.toList());
		
		
		
		
		return rlist.size();
		
	}
	/*
	 * it would accept the dates from the user and return the list of all 
	 * the recharges done in the period
	 */

	@Override
	public List<Recharge> findAllRechargesInPeriod(LocalDate startDate, LocalDate endDate) {
		
		logger.info("******** findAllRechargesInPeriod **** ");
		
			
		List<Recharge> rlist=new ArrayList<Recharge>();
		rlist=rechargeRepository.findAll();
		if (rlist.isEmpty()) {
			throw new RechargeException("No recharges Present");
		}
	rlist=	rlist.stream().filter(item->(item.getPurchasedDate().equals(startDate) || (item.getPurchasedDate().isAfter(startDate) && item.getPurchasedDate().isBefore(endDate) )||item.getPurchasedDate().equals(endDate))).collect(Collectors.toList());
	Collections.sort(rlist, new Comparator<Recharge>() {
        public int compare(Recharge o1, Recharge o2) {
         
         
            	return ~(o1.getPurchasedDate().compareTo(o2.getPurchasedDate())) ;
       
            
    }
});
		
		return rlist;
		
	}
	/*
	 * it would accept the dates from the user and return the number of all 
	 * the recharges done in the period
	 * 
	 */

	@Override
	public int countRechargesInPeriod(LocalDate startDate, LocalDate endDate) {
		
		
		logger.info("******** countRechargesInPeriod  **** ");
		
		List<Recharge> rlist=new ArrayList<Recharge>();
		rlist=rechargeRepository.findAll();
		if (rlist.isEmpty()) {
			throw new RechargeException("No recharges Present");
		}
	rlist=	rlist.stream().filter(item->(item.getPurchasedDate().equals(startDate) || (item.getPurchasedDate().isAfter(startDate) && item.getPurchasedDate().isBefore(endDate) )||item.getPurchasedDate().equals(endDate))).collect(Collectors.toList());
		
		return rlist.size();
		
	}
	/*
	 * it would accept the dates from the user and returns total revenue  
	 * in that time frame.
	 */

	@Override
	public double totalRevenueInPeriod(LocalDate startDate, LocalDate endDate) {
		
		logger.info("******** totalRevenueInPeriod  **** ");
	
			
		List<Recharge> rlist=new ArrayList<Recharge>();
		rlist=rechargeRepository.findAll();
		if (rlist.isEmpty()) {
			throw new RechargeException("No recharges Present");
		}
	rlist=	rlist.stream().filter(item->(item.getPurchasedDate().equals(startDate) || (item.getPurchasedDate().isAfter(startDate) && item.getPurchasedDate().isBefore(endDate) )||item.getPurchasedDate().equals(endDate))).collect(Collectors.toList());
		double sum=0;
	for(Recharge rec:rlist) {
		sum=sum+rec.getAmount();
	}
		
		return sum;
	
	}
	/*
	 * it would take packId as a parameter and return the recharge count 
	 * 
	*/

	@Override
	public int rechargesCountOnPack(Long id) {
		logger.info("******** count Recharges **** ");
	
		
	
		List<Recharge> rlist=rechargeRepository.findAll();
		if (rlist.isEmpty()) {
			throw new RechargeException("No recharges Present");
		}
		
		
		
		List<Recharge> newlist=new ArrayList<Recharge>();
	
		for(Recharge rec:rlist) {
			if(id==rec.getPack().getId()) {
				newlist.add(rec);
			}
		}
		return newlist.size();
		
	}
	/*
	 * takes in accountId and rechargeId and compares the validity of the pack  
	 * and if it is over it notifies the user.
	 */

	@Override
	public String expireIfValidityFinished(Long accountId, Long rechargeId) {
		
		logger.info("******** Running expireIfValidityFinished**** ");

		if (accountId==null || rechargeId==null) {
			throw new RechargeException("The accountId and rechargeId should not be null");
		}
		Recharge recharge=rechargeRepository.findById(rechargeId);
		if(recharge==null) {
			throw new RechargeException("The  recharge does not exits");
		}
		Account account=arepo.findById(accountId).orElse(null);
		if(account==null) {
			throw new RechargeException("The  Account does not exits");
		}
		LocalDate date=LocalDate.now();
		LocalDate purchaseDate=recharge.getPurchasedDate();
		LocalDate validity = purchaseDate.plusDays(recharge.getDaysValidity());
		if(validity.isBefore(date)) {
			recharge.setActive(false);
			account.setCurrentPack(null);
			return "Pack expired recharge for activating pack" ;
		}
		else {
			return "Pack is not expired";
		}
		
		
		
		
		
	}
	/*
	 * This method is used for getting the total recharges done by users.
	 * 
	 */

	@Override
	public List<Recharge> listall() {
		logger.info("******** finding AllRecharges **** ");
		
         List<Recharge> rlist=rechargeRepository.findAll();

 		if (rlist.isEmpty()) {
 			throw new RechargeException("No recharges Present");
 		}
 		Collections.sort(rlist, new Comparator<Recharge>() {
            public int compare(Recharge o1, Recharge o2) {
             
             
                	return ~(o1.getPurchasedDate().compareTo(o2.getPurchasedDate())) ;
           
                
        }
    });
		return rlist;
	}

}