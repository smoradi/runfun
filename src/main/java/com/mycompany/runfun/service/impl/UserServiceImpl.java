package com.mycompany.runfun.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.runfun.domain.User;
import com.mycompany.runfun.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User findByUsername(String username) {
		List<User> list = User.findUsersByUsernameEquals(username).getResultList();
		if (list.size() != 1) {
			return null;
		}
		return list.get(0);
	}

}
