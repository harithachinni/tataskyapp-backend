package com.cg.apps.tataskyapp.services;




import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cg.apps.tataskyapp.entities.Account;
import com.cg.apps.tataskyapp.services.IAccountService;
import com.cg.apps.tataskyapp.exception.UserNotFound;
import com.cg.apps.tataskyapp.exception.UserNotFoundException;
import com.cg.apps.tataskyapp.entities.User;
import com.cg.apps.tataskyapp.repository.IUserRepository;

@Service
public class IUserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository uDao;
	@Autowired
	private IAccountService aService;

	//For Log
	private Logger logger = LoggerFactory.getLogger(IUserServiceImpl.class);

	
	//register new user service implementation
	
	@Override
	public User register(User user) {
		
		logger.info("*** Creating User**");
		
		User usr = new User(user);
		Account acc = aService.add(new Account());
		usr.setAccount(acc);
		usr = uDao.save(usr);
		acc.setUser(usr);
		System.out.println(acc);
		aService.update(acc);
		return usr;
	}

	
	// Update the details of the user
	@Override
	public User update(User user) {
		
		logger.info("*** Updating user**" );
		
		Optional<User> opt = uDao.findById(user.getId());
		if (!opt.isPresent()) {
			throw new UserNotFoundException("User not found for id: " + user.getId());
		}
		User usr = opt.get();
		usr.copy(user);
		usr = uDao.save(user);
		return usr;
	}
	
	
	// Find any User by Id 
	@Override
	public User findById(Long id) {
		
		logger.info("*** Finding user by id**" + id);
		
		Optional<User> opt = uDao.findById(id);
		if (!opt.isPresent()) {
			throw new UserNotFoundException("User not found for id: " + id);
		}
		User usr = opt.get();
		return usr;
	}

	// Find User by Username
	@Override
	public User findByUsername(String username) {
		
		logger.info("*** Finding user by username**" + username);
		
		User usr = uDao.findByUsername(username);
		if (usr == null) {
			throw new UserNotFoundException("User Not found for username: " + username);
		}
		return usr;
	}

	// Delete User with userId
	@Override
	public void deleteByUserId(Long userId) {
		
		logger.info("*** delete user by id**" + userId);
		
		Optional<User> opt = uDao.findById(userId);
		if (!opt.isPresent()) {
			throw new UserNotFoundException("User not found for id: " + userId);
		}
		uDao.deleteById(userId);

	}



	// Checks if the username and password is correct
	@Override
	public boolean validateUser(String username, String password) throws UserNotFoundException {
		boolean flag = false;

		User user = uDao.findByUsername(username);

		if (user == null)
			throw new UserNotFoundException("Please check User Name");

		else if (password.equals(user.getPassword()))
			flag = true;
		else
			throw new UserNotFoundException("Please check password");

		return flag;
	}
	
	
	//check if the username provided is active user or not
	public boolean checkValidUser(String username, String password) {
		boolean flag = false;
		User user = uDao.findByUsername(username);
		if(user.isActive()) {
			flag = validateUser(user.getUsername(),password);
			return flag;
		}
		else {
			return flag;
		}
	}
	
	
}