package com.ooad.InClassComp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user_competition", uniqueConstraints=@UniqueConstraint(columnNames={"user_id","competition_id"}))
public class Submission implements Serializable {

	private static final long serialVersionUID = -8011995590702201312L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="competition_id")
	private Competition competition;
	
	@OneToMany(
	        mappedBy = "submission", 
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true
	    )
	private Set<UploadSubmission> uploadedSubmissions = new HashSet<UploadSubmission>();

	private Date joinedDate = new Date();

	
	public Submission(User user, Competition competition) {
		super();
		this.user = user;
		this.competition = competition;
	}
	public Submission() {
		super();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public Set<UploadSubmission> getUploadedSubmissions() {
		return uploadedSubmissions;
	}

	public void setUploadedSubmissions(Set<UploadSubmission> uploadedSubmissions) {
		this.uploadedSubmissions = uploadedSubmissions;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
}
