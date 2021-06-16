package com.cg.apps.tataskyapp.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.dto.AccountToForRec;
import com.cg.apps.tataskyapp.dto.AddRequest;
import com.cg.apps.tataskyapp.dto.RechargeTo;
import com.cg.apps.tataskyapp.dto.ServiceRequestTo;
import com.cg.apps.tataskyapp.dto.UserToForRech;
import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.entities.Pack;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.entities.User;
import com.cg.apps.tataskyapp.repository.IAccountRepository;
import com.cg.apps.tataskyapp.repository.IPackRepository;
import com.cg.apps.tataskyapp.services.IRechargeServiceImpl;




@RestController
@RequestMapping(value="/recharge")

@CrossOrigin(origins="*")


public class RechargeController {
	/*
	 * 
	 * controls the data flow into model object and updates the view whenever data changes.
	 */

	
	@Autowired
	private IRechargeServiceImpl rService;
	@Autowired
    private IPackRepository prepo;
	@Autowired
    private IAccountRepository arepo;
	
	
	//https://localhost:8080/recharge/create/{id}/{accountId}
//	@PostMapping(value = "/create/{packId}/{accountId}")
//	public ResponseEntity<Recharge> createRecharge( @PathVariable  Long packId,@PathVariable Long accountId)
//	
//	{
//		Recharge rec =rService.createRecharge( packId, accountId);
//		ResponseEntity<Recharge> entity = new ResponseEntity<Recharge>(rec,HttpStatus.CREATED);
//		return  entity;
//
//}
	@PostMapping("/add")
	public Recharge createRecharge(@RequestBody AddRequest request) {

		
		Pack pack=prepo.findById(request.getPackId()).orElse(null);
	Account account=arepo.findById(request.getAccountId()).orElse(null);
		Recharge rech = rService.createRecharge(pack, account);
	
		
		return rech;
	}
	
	  //  URL: https://localhost:8080/recharge/findByPurchaseDate/{accountId}
	 
	
	@GetMapping("/findByPurchaseDate/{accountId}")
	public ResponseEntity<List<Recharge>> findRechargesForUserInDescendingOrderByPurchasedDate(@PathVariable Long accountId) {
		List<Recharge>  rlist=rService.findRechargesForUserInDescendingOrderByPurchasedDate(accountId);
		 ResponseEntity<List<Recharge>> entity = new  ResponseEntity<List<Recharge>>(rlist,HttpStatus.OK);
		return entity;
	}
	

	//https://localhost:8080/recharge/rechargeCount/{accountId}
	
	@GetMapping("/rechargeCount/{accountId}")
	public  ResponseEntity<Object> rechargesForUserCount(@PathVariable Long accountId) {
		int count=rService.rechargesForUserCount(accountId);
		ResponseEntity<Object> rentity=new ResponseEntity<Object>(count,HttpStatus.OK);
		return rentity;
	}
	
	// URL: https://localhost:8080/recharge/rechargesForPeriod/{startDate}/{endDate}
	 
	@GetMapping("/rechargesForPeriod/{startDate}/{endDate}")
	public ResponseEntity<List<Recharge>> findAllRechargesInPeriod(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,@PathVariable @DateTimeFormat (pattern = "yyyy-MM-dd")  LocalDate endDate) {
		
		List<Recharge>  rlist=rService.findAllRechargesInPeriod(startDate,endDate);
		 ResponseEntity<List<Recharge>> entity = new  ResponseEntity<List<Recharge>>(rlist,HttpStatus.OK);
		 return entity;
	}
	
	// URL: https://localhost:8080/recharge/rechargeCountForPeriod/{startDate}/{endDate}
	
	@GetMapping("/rechargeCountForPeriod/{startDate}/{endDate}")
	public  ResponseEntity<Object> countRechargesInPeriod(@PathVariable @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate,@PathVariable @DateTimeFormat (pattern = "MM-dd-yyyy")  LocalDate endDate) {
		int count=rService.countRechargesInPeriod(startDate,endDate);
		ResponseEntity<Object> rentity=new ResponseEntity<Object>(count,HttpStatus.OK);
		return rentity;
	}
	
	// URL: https://localhost:8080/recharge/totalAmount/{startDate}/{endDate}
	
	@GetMapping("/totalAmount/{startDate}/{endDate}")
	public  ResponseEntity<Object> totalRevenueInPeriod(@PathVariable @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate, @PathVariable @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate endDate) {
		double amount=rService.totalRevenueInPeriod(startDate,endDate);
		ResponseEntity<Object> rentity=new ResponseEntity<Object>(amount,HttpStatus.OK);
		return rentity;
	}
	
	// URL: https://localhost:8080/recharge/rechargesCount/{packId}

	@GetMapping("/rechargesCountOnPack/{packId}")
	public  ResponseEntity<Object> rechargesCount(@PathVariable Long packId) {
	int rechargesForPack=rService.rechargesCountOnPack(packId);
	ResponseEntity<Object> rentity=new ResponseEntity<Object>(rechargesForPack,HttpStatus.OK);
	return rentity;
	}
	
	// URL: https://localhost:8080/recharge/validity/{accountId}/{rechargeId}
	
	@GetMapping("/validity/{accountId}/{rechargeId}")
	public ResponseEntity<Object>  expireIfValidityFinished(@PathVariable Long accountId,@PathVariable Long rechargeId) {
		String message=rService.expireIfValidityFinished(accountId,rechargeId);
		ResponseEntity<Object> entity = new ResponseEntity<Object>(message,HttpStatus.OK);
		return entity;
		
	}
	
	// URL: https://localhost:8080/recharge/totalRecharges

	@GetMapping("/totalRecharges")
	public ResponseEntity<List<Recharge>> listall(){
		List<Recharge>  rlist=rService.listall();
		 ResponseEntity<List<Recharge>> entity = new  ResponseEntity<List<Recharge>>(rlist,HttpStatus.OK);
		 return entity;
	}

}
