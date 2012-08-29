package com.mycompany.runfun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.runfun.domain.Record;
import com.mycompany.runfun.domain.User;
import com.mycompany.runfun.service.RecordService;
import com.mycompany.runfun.service.UserService;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	protected UserService userService;
	
	@Override
	public Record findByUsernameAndId(String username, Long id) {
		User user= userService.findByUsername(username);
		if (user == null)
			return null;
		List<Record> list = Record.findRecordsByUserAndIdEquals(user, id).getResultList();
		if (list.size() != 1)
			return null;
		return list.get(0);
	}

}
