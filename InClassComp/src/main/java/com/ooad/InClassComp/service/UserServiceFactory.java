package com.ooad.InClassComp.service;

import org.springframework.stereotype.Service;

import com.ooad.InClassComp.model.User;
import com.ooad.InClassComp.ui.model.SignUpUser;

@Service
public class UserServiceFactory {

	public static void createUser(SignUpUser signUpUser) {
		UserFacade userFacade = new UserFacadeImpl();
		User user = new User();
		user.setEmail(signUpUser.getEmail());
		user.setUserName(signUpUser.getUserName());
		user.setPassword(signUpUser.getPassword());
		user.setAccepted(Boolean.FALSE);
		user.setType(signUpUser.getType());
		userFacade.saveUser(user);
	}
}
