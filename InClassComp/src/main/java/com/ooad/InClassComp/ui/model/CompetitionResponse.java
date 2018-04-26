package com.ooad.InClassComp.ui.model;

import java.util.Date;
import java.util.Iterator;

import com.ooad.InClassComp.model.Competition;
import com.ooad.InClassComp.model.TestCriteria;

public class CompetitionResponse {

	private String competitionName;
	private String description;
	private Date startDate;
	private Date endDate;
	private Long id;
	private String className;

	
	public String getCompetitionName() {
		return competitionName;
	}
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public CompetitionResponse(String competitionName, String description, 
			Date startDate, Date endDate, Long id,String className) {
		super();
		this.competitionName = competitionName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.id = id;
		this.className= className;
	}

	public CompetitionResponse(Competition competition) {
		super();
		this.competitionName = competition.getCompetitionName();
		this.description = competition.getDescription();
		this.startDate = competition.getCreatedDate();
		this.endDate = competition.getEndDate();
		this.id = competition.getId();
		this.className = competition.getClassName();

	}
	
}

