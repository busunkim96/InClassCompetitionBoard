package com.ooad.InClassComp.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ooad.InClassComp.doa.UserDAO;
import com.ooad.InClassComp.model.User;
import com.ooad.InClassComp.ui.model.UserResponse;

@Controller
public class AdminController {
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value="/admin/getAcceptedUsers", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<UserResponse> getAcceptedUsers() {
		List<User> acceptedUsers = null;
		List<UserResponse> results = new ArrayList<UserResponse>();
		try {
			acceptedUsers = userDAO.findByAccepted(Boolean.TRUE);
		}
		catch (Exception ex) {
			return null;
		}
		for(User user : acceptedUsers) {
			UserResponse response = new UserResponse(user);
			results.add(response);
		}
		return results;
	}
	
	@RequestMapping(value="/admin/getUsersTobeRegistered", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<UserResponse> getUsersTobeRegistered() {
		List<User> acceptedUsers = null;
		try {
			acceptedUsers = userDAO.findByAccepted(Boolean.FALSE);
		}
		catch (Exception ex) {
			return null;
		}
		List<UserResponse> results = new ArrayList<UserResponse>();
		for(User user : acceptedUsers) {
			UserResponse response = new UserResponse(user);
			results.add(response);
		}
		return results;
	}
	
	@RequestMapping(value="/admin/acceptUsers", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String acceptUsers(@RequestParam(value="userIds", required=false) List<Long> userIds ) {
		List<User> users = null;
		try {
			users = (List<User>) userDAO.findAllById(userIds);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			for(User user : users) {
				user.setAccepted(Boolean.TRUE);
				userDAO.save(user);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "FAILED";
		}
		return "SUCCESS";
	}


}
