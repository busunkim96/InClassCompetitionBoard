package com.ooad.InClassComp.ui.model;

import java.util.*;

import com.ooad.InClassComp.model.Competition;
import com.ooad.InClassComp.model.Submission;
import com.ooad.InClassComp.model.TestCriteria;
import com.ooad.InClassComp.model.UploadSubmission;

public class CompetitionResponse {

	private String competitionName;
	private String description;
	private Date startDate;
	private Date endDate;
	private Long id;
	private String className;
	private List<UserResponse> userResponseList = new ArrayList<UserResponse>();


	private List<Upload> submissions = new ArrayList<Upload>();

	public List<UserResponse> getUserResponseList() {
		return userResponseList;
	}

	public List<Upload> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Upload> submissions) {
		this.submissions = submissions;
	}
	public void setUserResponseList(List<UserResponse> userResponseList) {
		this.userResponseList = userResponseList;
	}
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
		Iterator<Submission> iterator = competition.getCompetitionUserSubmissions().iterator();
		while(iterator.hasNext()) {
			Submission submission = iterator.next();
			UserResponse userResponse = new UserResponse(submission.getUser());
			this.userResponseList.add(userResponse);
			List<UploadSubmission> uploads = new ArrayList<UploadSubmission>(submission.getUploadedSubmissions());
			if(uploads.size() > 0) {
				Collections.sort(uploads,new Comparator<UploadSubmission>(){
					@Override
					public int compare(UploadSubmission a, UploadSubmission b) {
						return b.getUploadTime().compareTo(a.getUploadTime());
					}
				});
				UploadSubmission upload = uploads.get(0);
				Upload targetUpload = new Upload();
				targetUpload.setData(new String(upload.getData()));
				targetUpload.setUploadDate(upload.getUploadTime().toString());
				targetUpload.setScore(upload.getScore());
				this.submissions.add(targetUpload);
			}
		}

	}

}

