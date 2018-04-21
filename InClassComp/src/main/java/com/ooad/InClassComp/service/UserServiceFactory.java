package com.ooad.InClassComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooad.InClassComp.model.User;
import com.ooad.InClassComp.ui.model.SignUpUser;

@Service
public class UserServiceFactory {

	@Autowired
	UserFacade userFacade;
	
	public void createUser(SignUpUser signUpUser) {
		User user = new User();
		user.setEmail(signUpUser.getEmail());
		user.setUserName(signUpUser.getUserName());
		user.setPassword(signUpUser.getPassword());
		user.setAccepted(Boolean.FALSE);
		user.setType(signUpUser.getType());
		try {
			userFacade.saveUser(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
