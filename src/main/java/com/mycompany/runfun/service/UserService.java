package com.mycompany.runfun.service;

import com.mycompany.runfun.domain.User;

public interface UserService {

	User findByUsername(String username);
	
}
