package com.cg.apps.tataskyapp.services;

import com.cg.apps.tataskyapp.exception.LoginException;
import com.cg.apps.tataskyapp.exception.UserNotFoundException;

public interface ILoginService {
	
	public boolean login(String username, String password)throws LoginException, UserNotFoundException;

}
