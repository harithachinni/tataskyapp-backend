package com.cg.apps.tataskyapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.apps.tataskyapp.exception.LoginException;
import com.cg.apps.tataskyapp.exception.UserNotFoundException;



@Service
public class ILoginServiceImpl implements ILoginService{
	
	@Autowired
	IUserServiceImpl service;

	@Override
	public boolean login(String username, String password) throws LoginException, UserNotFoundException {
		boolean flag = false;

		if (username == null) {
			flag = false;
		}
		if (!service.checkValidUser(username,password)) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}
}
