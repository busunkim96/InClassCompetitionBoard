package com.ooad.InClassComp.ui.model;


import com.ooad.InClassComp.model.User;

public class UserResponse {
	private String userName;
	private Integer type;
	private String email;
	private String school;
	private String phoneNumber;
	private String about;
	private Long id;
	private Boolean accepted;
	
	public UserResponse(User user) {
		this.setUserName(user.getUserName());
		this.type = user.getType();
		this.email = user.getEmail();
		this.school = user.getSchool();
		this.phoneNumber = user.getPhoneNumber();
		this.about = user.getAbout();
		this.id = user.getId();
		this.accepted = user.getAccepted();

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

}
