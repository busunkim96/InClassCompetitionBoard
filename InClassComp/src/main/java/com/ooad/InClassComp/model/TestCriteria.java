package com.ooad.InClassComp.model;

import java.io.Serializable;
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
@Table(name="competition_criteria")
public class TestCriteria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="competition_id")
	private Competition competition;
	
	@NotNull
	private String fileName;
	
	private Date uploadTime = new Date();
	
	@Lob
	@Nullable
	@Column(name="file",length=5120000)
	private byte[] data;
	
	
	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
	
		this.fileName = fileName;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public TestCriteria(String fileName, Date uploadTime, byte[] data) {
		super();
		this.fileName = fileName;
		this.uploadTime = uploadTime;
		this.data = data;
	}
	
	public TestCriteria() {
		super();
	}
	
	
	
}
