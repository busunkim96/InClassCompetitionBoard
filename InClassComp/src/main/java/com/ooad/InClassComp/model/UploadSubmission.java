package com.ooad.InClassComp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity
@Table(name="user_submission")
public class UploadSubmission {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="submission_id")
	private Submission submission;
	
	@Lob
	@Nullable
	@Column(name="file",length=5120000)
	private byte[] data;
	
	@NotNull
	private Date uploadTime;
	
	
	@Nullable
	private Long score;


	public Submission getSubmission() {
		return submission;
	}


	public void setSubmission(Submission submission) {
		this.submission = submission;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}


	public Date getUploadTime() {
		return uploadTime;
	}


	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}


	public Long getScore() {
		return score;
	}


	public void setScore(Long score) {
		this.score = score;
	}


	public UploadSubmission(Submission submission, byte[] data, @NotNull Date uploadTime, Long score) {
		super();
		this.submission = submission;
		this.data = data;
		this.uploadTime = uploadTime;
		this.score = score;
	}
	
	public UploadSubmission() {
		super();
	}
	public Long getId() {
		return id;
	}
	
}
