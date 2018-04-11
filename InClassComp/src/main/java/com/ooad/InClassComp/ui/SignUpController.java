package com.ooad.InClassComp.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ooad.InClassComp.service.UserServiceFactory;
import com.ooad.InClassComp.ui.model.ResponseEntity;
import com.ooad.InClassComp.ui.model.SignUpUser;


@Controller
public class SignUpController {
	
	@RequestMapping(value="/register/User", method=RequestMethod.POST)
	public String createUser(@ModelAttribute SignUpUser signUpUser,Model model) {
		ResponseEntity response = new ResponseEntity();
		try {
			UserServiceFactory.createUser(signUpUser);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
			model.addAttribute("response",response);
			return "signUp";
		}
		response.setStatus("SUCCESS");
		response.setStatus("User is registered, please await for approval");
		model.addAttribute("response",response);
		return "signUp";
	}
	
	@RequestMapping("/register")
	public String signUp(Model model) {
		model.addAttribute("signUpUser", new SignUpUser());
		return "registration";
	}
}
