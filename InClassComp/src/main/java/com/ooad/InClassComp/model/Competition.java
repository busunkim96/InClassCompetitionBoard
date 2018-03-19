package com.ooad.InClassComp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "competition")
public class Competition {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User createdBy;

	@NotNull
	private String competitionName;

	@NotNull
	private Date createdDate;

	@NotNull
	private Date endDate;

	@Nullable
	private String className;
	
	@OneToMany(
	        mappedBy = "competition", 
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true
	    )
	private Set<Submission> competitionUserSubmissions = new HashSet<Submission>();

	@OneToMany(
	        mappedBy = "competition", 
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true
	    )
	private Set<TestCriteria> competitionCriteria = new HashSet<TestCriteria>();


	@Nullable
	@Column(length = 4000)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	public Competition(Long id, @NotNull String competitionName, @NotNull Date createdDate, @NotNull Date endDate,
			String description,String className) {
		super();
		this.id = id;
		this.competitionName = competitionName;
		this.createdDate = createdDate;
		this.endDate = endDate;
		this.description = description;
		this.className = className;
	}

	public Competition() {
		super();
	}

	public void setCompetitionUserSubmissions(Set<Submission> competitionUserSubmissions) {
		this.competitionUserSubmissions = competitionUserSubmissions;
	}

	public Set<Submission> getCompetitionUserSubmissions() {
		return competitionUserSubmissions;
	}

	public Set<TestCriteria> getCompetitionCriteria() {
		return competitionCriteria;
	}

	public void setCompetitionCriteria(Set<TestCriteria> competitionCriteria) {
		this.competitionCriteria = competitionCriteria;
	}
	
	

}
