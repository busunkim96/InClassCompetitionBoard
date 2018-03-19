package com.ooad.InClassComp.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ooad.InClassComp.doa.UserDAO;
import com.ooad.InClassComp.model.User;
import com.ooad.InClassComp.ui.model.SignUpUser;

@Controller
public class LoginController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/login/User")
	public String loginUser(@ModelAttribute SignUpUser userDetails) {
		List<User> users = null;
		try {
			users  = (List<User>)userDAO.findByUserName(userDetails.getUserName());
			if(users == null) {
				return "login";
			} else if(users != null && users.size() > 1) {
				return "login";
			} else  {
				User user = users.get(0);
				if(user.getPassword().equals(userDetails.getPassword())) {
					return "home";
				} else {
					return "login";
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "login";
	}
	
	@RequestMapping("/login")
	public String signUp(Model model) {
		return "login";
	}
}
