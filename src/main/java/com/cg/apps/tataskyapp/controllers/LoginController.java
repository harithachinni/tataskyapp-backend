package com.cg.apps.tataskyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.services.ILoginService;
import com.cg.apps.tataskyapp.services.ILoginServiceImpl;


@RestController
@RequestMapping(value = "/login")

public class LoginController {

	@Autowired
	private ILoginService service;
	
	
	@GetMapping(value = "/authenticate/{username}/{password}")

	public ResponseEntity<String> viewUser(@PathVariable String username, @PathVariable String password) {
		ResponseEntity<String> rentity;
		if (service.login(username,password)) {
			rentity = new ResponseEntity<String>("Login Successful.", HttpStatus.ACCEPTED);
		} else {
			rentity = new ResponseEntity<String>("Login Failed.", HttpStatus.UNAUTHORIZED);
		}
		return rentity;
	}
}
