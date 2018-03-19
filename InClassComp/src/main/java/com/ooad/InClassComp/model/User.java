package com.ooad.InClassComp.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "user")
public class User {
	
	@NotNull
	@Column(name="user_name",unique=true)
	private String userName;
	
	@NotNull
	private String password;
	
	@NotNull
	private Integer type;
	
	@NotNull
	private String email;
	
	
	@Nullable
	private String school;
	
	@Nullable
	private String phoneNumber;
	
	@Nullable
	private String about;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private Boolean accepted;
	
	@Nullable
	@ElementCollection
	@CollectionTable(name="classNames", joinColumns=@JoinColumn(name="student_id"))
	@Column(name="className")
	private List<String> className; 
	
	@OneToMany(
	        mappedBy = "user",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private Set<Submission> competitions = new HashSet<Submission>();
	
	public User() {
		super();
	}

	
	public User(@NotNull String userName, @NotNull String password, @NotNull Integer type, @NotNull String email,
			String school, String phoneNumber, String about, @NotNull Boolean accepted, List<String> className) {
		super();
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.email = email;
		this.school = school;
		this.phoneNumber = phoneNumber;
		this.about = about;
		this.accepted = accepted;
		this.className = className;
	}
	


	public User(@NotNull String userName, @NotNull String password, @NotNull Integer type, @NotNull String email,
			@NotNull Boolean accepted) {
		super();
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.email = email;
		this.accepted = accepted;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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


	public List<String> getClassName() {
		return className;
	}

	public void setClassName(List<String> className) {
		this.className = className;
	}


	public Set<Submission> getCompetitions() {
		return competitions;
	}


	public void setCompetitions(Set<Submission> competitions) {
		this.competitions = competitions;
	}



}
