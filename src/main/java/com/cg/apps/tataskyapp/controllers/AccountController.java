 package com.cg.apps.tataskyapp.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.dto.AccountTo;
import com.cg.apps.tataskyapp.dto.RechargeToForAcc;
import com.cg.apps.tataskyapp.dto.UserTo;
import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.services.IAccountService;
import com.cg.apps.tataskyapp.services.IAccountServiceImpl;
import com.cg.apps.tataskyapp.exception.AccountNotFoundException;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.dto.ServiceRequestTo;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.entities.User;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/accounts")
//http://localhost:8080/accounts	
@Validated
public class AccountController {
	org.slf4j.Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private IAccountService aService;

    //to test controller	
	// http://localhost:8080/accounts/hello
	
	@RequestMapping("/hello")
	
	public String sayHello() {
		logger.info("******** Hello *****");

		return "saying hello";
	}
   
    //to add a new account
	// http://localhost:8080/accounts/add
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public Account addAccount(@RequestBody Account account) {
		logger.info("******** Account Created*****");
		Account acc = aService.add(account);
		return acc;
	}
      /*to find account details using account id 
       * this uses AccountTo and UserTo data transfer objects
       * to remove cyclic dependency because user and account
       *  have bidirectional one to one mapping 
       */
	
    	// http://localhost:8080/accounts/find/id/{accountId}
	
	@GetMapping("/find/id/{accountId}")
	public AccountTo findById(@PathVariable("accountId") Long accountId) throws AccountNotFoundException {
		
		logger.info("******** Account Find By Id *****");
		
		Account acc = aService.findById(accountId);
		AccountTo accountTo = new AccountTo(acc);
		User usr = acc.getUser();
		UserTo uto = new UserTo(usr);
		accountTo.setUser(uto);
		List<Recharge> rech = acc.getRecharges();
		List<RechargeToForAcc> rechList = new ArrayList<RechargeToForAcc>();
		for (Recharge recharge : rech) {
			rechList.add(new RechargeToForAcc(recharge));
		}
		List<ServiceRequestTo> serReqTo = new ArrayList<ServiceRequestTo>();
		List<ServiceRequest> sReq = acc.getRequests();
		for (ServiceRequest serviceRequest : sReq) {
			serReqTo.add(new ServiceRequestTo(serviceRequest));
		}
		accountTo.setRequests(serReqTo);
		accountTo.setRech(rechList);
		return accountTo;
	}

    /*to update an account
     * this also uses data transfer objects AccountTo and UserTo
     */
	// http://localhost:8080/accounts/update
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PutMapping("/update")
	public AccountTo update(@RequestBody Account account) {
		
		logger.info("******** Account Updated *****");
		
		Account acc = aService.update(account);
		AccountTo accountTo = new AccountTo(acc);
		User usr = acc.getUser();
		UserTo uto = new UserTo(usr);
		accountTo.setUser(uto);
		List<Recharge> rech = acc.getRecharges();
		List<RechargeToForAcc> rechList = new ArrayList<RechargeToForAcc>();
		for (Recharge recharge : rech) {
			rechList.add(new RechargeToForAcc(recharge));
		}
		List<ServiceRequestTo> serReqTo = new ArrayList<ServiceRequestTo>();
		List<ServiceRequest> sReq = acc.getRequests();
		for (ServiceRequest serviceRequest : sReq) {
			serReqTo.add(new ServiceRequestTo(serviceRequest));
		}
		accountTo.setRequests(serReqTo);
		accountTo.setRech(rechList);
		return accountTo;
	}

    //to delete a user's account by account id 
	// http://localhost:8080/accounts/delete/id/{accountId}
	
	@DeleteMapping("/delete/id/{accountId}")
	public  ResponseEntity<String> deleteByAccountId(@PathVariable("accountId") Long accountId) {
		
		logger.info("******** Account Deleted By Id *****");
		
		aService.deleteByAccountId(accountId);
		ResponseEntity<String> rentity=new ResponseEntity<String>("Account with " +accountId+ " is deleted from account table",HttpStatus.OK);
		return rentity;
	}

    //returns the number of accounts created in a given period using client entered start and end date 
	// http://localhost:8080/accounts/count/in/period/{startDate}/{endDate}
	// http://localhost:8080/accounts/count/in/period/05-01-2021/05-17-2025
	
	@GetMapping("/count/in/period/{startDate}/{endDate}")
	public  ResponseEntity<String> countInPeriod(@PathVariable("startDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate endDate) {
		
		logger.info("******** Get the Number Of Accounts created in between {startDate}   {endDate}*****");
		
		int accountInPeriod = aService.countCreatedAccountsInPeriod(startDate, endDate);
		ResponseEntity<String> rentity=new ResponseEntity<String>("Total Number of Accounts in Account Table in betrwwen start and end date is :" +accountInPeriod,HttpStatus.OK);
		return rentity;
	}


    //returns total number of accounts created 
	// http://localhost:8080/accounts/count/accounts
	
	@GetMapping("/count/accounts")
	public ResponseEntity<String> countAccounts() {
		logger.info("******** Count the Total Number of Accounts in Account Table *****");
		int countAccounts = aService.countAccounts();
		ResponseEntity<String> rentity=new ResponseEntity<String>("Total Number of Accounts in Account Table is :"+countAccounts,HttpStatus.OK);
		return rentity;
	}

}
