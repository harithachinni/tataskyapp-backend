package com.cg.apps.tataskyapp.services;

import java.util.Optional;

import com.cg.apps.tataskyapp.exception.UserNotFound;
import com.cg.apps.tataskyapp.exception.UserNotFoundException;
import com.cg.apps.tataskyapp.entities.User;

public interface IUserService {

	User register(User user);

	User update(User user);

	User findById(Long id);

	User findByUsername(String username);
	
	void deleteByUserId(Long userId);
	
	public boolean validateUser(String username,String password) throws UserNotFoundException;
	
	public boolean checkValidUser(String username, String password);

}
