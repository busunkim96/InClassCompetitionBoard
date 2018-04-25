package com.ooad.InClassComp.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ooad.InClassComp.doa.UserDAO;
import com.ooad.InClassComp.model.User;
import com.ooad.InClassComp.ui.model.SignUpUser;

@Controller
@CrossOrigin
public class LoginController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/login/User",  method= RequestMethod.POST)
	@ResponseBody
	public User loginUser(@ModelAttribute SignUpUser userDetails) {
		List<User> users = null;
		try {
			users  = (List<User>)userDAO.findByUserName(userDetails.getUserName());
			if(users == null || users.size() == 0) {
				return null;
			} else if( users.size() > 1) {
				return null;
			} else  {
				User user = users.get(0);
				if(user.getPassword().equals(userDetails.getPassword())) {
					return user;
				} else {
					com.ooad.InClassComp.ui.model.ResponseEntity response  = new com.ooad.InClassComp.ui.model.ResponseEntity();
					response.setMessage("USER NOT FOUND");
					ResponseEntity<com.ooad.InClassComp.ui.model.ResponseEntity> responseEntity =
							new  ResponseEntity<com.ooad.InClassComp.ui.model.ResponseEntity>(response,HttpStatus.BAD_REQUEST);
					return null;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/login")
	public String signUp(Model model) {
		return "login";
	}
}
