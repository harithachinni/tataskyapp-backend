package com.cg.apps.tataskyapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.dto.RechargeToForAcc;
import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.exception.UserNotFound;
import com.cg.apps.tataskyapp.exception.UserNotFoundException;
import com.cg.apps.tataskyapp.entities.Recharge;
import com.cg.apps.tataskyapp.dto.ServiceRequestTo;
import com.cg.apps.tataskyapp.entities.ServiceRequest;
import com.cg.apps.tataskyapp.dto.AccountToUser;
import com.cg.apps.tataskyapp.dto.UserDto;
import com.cg.apps.tataskyapp.entities.User;
import com.cg.apps.tataskyapp.services.IUserService;

@RestController
@RequestMapping("/user")
@Valid
public class UserController {
	@Autowired
	private IUserService uService;

	// to test user controller
	@RequestMapping("/test")
	public String test() {
		return "working fine";
	}
	

	// to add or to register a new user into database
	//http://localhost:8088/user/add
		@ResponseStatus(code = HttpStatus.CREATED)
		@PostMapping("/add")
		public UserDto register(@Valid @RequestBody User user) {
			User usr = uService.register(user);
			UserDto userTo1 = new UserDto(usr);
			Account acc = usr.getAccount();
			AccountToUser accountTo1 = new AccountToUser(acc);
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
			accountTo1.setRequests(serReqTo);
			accountTo1.setRecharges(rechList);
			userTo1.setAccount(accountTo1);
			return userTo1;
		}
	
	//to update user details 
	//http://localhost:8088/user/update
		@PutMapping("/update")
		@ResponseStatus(code=HttpStatus.ACCEPTED)
		public User update(@Valid @RequestBody User user) {
			return uService.update(user);
		}

	// to find user details by entering username
	//http://localhost:8088/user/by/username/
		@GetMapping("/by/username/{uname}")
		public UserDto findByUsername(@Valid @PathVariable("uname") String username) {
		User usr = uService.findByUsername(username);
		UserDto userTo1 = new UserDto(usr);
		Account acc = usr.getAccount();
		AccountToUser accountTo1 = new AccountToUser(acc);
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
		accountTo1.setRequests(serReqTo);
		accountTo1.setRecharges(rechList);
		userTo1.setAccount(accountTo1);

		return userTo1;

	}

	

	// to find user details by entering user's id
	//http://localhost:8088/user/by/id/
	@GetMapping("/by/id/{id}")
	public UserDto findById(@Valid @PathVariable("id") Long id) {
		User usr = uService.findById(id);
		UserDto userTo1 = new UserDto(usr);
		Account acc = usr.getAccount();
		AccountToUser accountTo1 = new AccountToUser(acc);
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
		accountTo1.setRequests(serReqTo);
		accountTo1.setRecharges(rechList);
		userTo1.setAccount(accountTo1);
		return userTo1;
	}

	// to delete user's information from the database by entering just the id
	//http://localhost:8088/user/delete/by/id/
	@DeleteMapping("delete/by/id/{id}")
	public void deleteByUserId(@Valid @PathVariable("id") Long userId) {
		uService.deleteByUserId(userId);
	}
	

}
