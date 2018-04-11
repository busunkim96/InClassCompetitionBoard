package com.ooad.InClassComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooad.InClassComp.doa.UserDAO;
import com.ooad.InClassComp.model.User;

@Service
public class UserFacadeImpl implements UserFacade {
	
	@Autowired
	public UserDAO userdao;
	
	@Override
	public void saveUser(User user) {
		userdao.save(user);
	}
	
}
