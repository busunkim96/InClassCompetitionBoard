package com.ooad.InClassComp.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ooad.InClassComp.ui.model.SignUpUser;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String home() {
		return "home1";
	}
}
