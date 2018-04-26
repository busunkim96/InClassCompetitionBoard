package com.ooad.InClassComp.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooad.InClassComp.doa.CompetitionDAO;
import com.ooad.InClassComp.doa.SubmissionDAO;
import com.ooad.InClassComp.doa.UserDAO;
import com.ooad.InClassComp.model.Competition;
import com.ooad.InClassComp.model.Submission;
import com.ooad.InClassComp.model.User;
import com.ooad.InClassComp.model.UserType;
@Service
public class CompetitionFacadeImpl implements CompetitionFacade {
	
	@Autowired
	CompetitionDAO competitionDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	SubmissionDAO submissionDAO;
	
	@Override
	public List<Competition> findAll() {
		return (List<Competition>)competitionDAO.findAll();
	}

	@Override
	public Optional<Competition> findById(Long compId) {
		return (Optional<Competition>)competitionDAO.findById(compId);
	}

	@Override
	public Competition save(Competition competition) {
		return competitionDAO.save(competition);
	}

	@Override
	public Long createCompetition(String userName, String competitionName, String endDate, String className,
			String description) {
		List<User> users = null;
		try {
			users = userDAO.findByUserName(userName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-DD", Locale.ENGLISH);
		Date endD = null;
		try {
			if(endDate != null) {
				endD= format.parse(endDate);
			}
		}catch(ParseException e) {
			e.printStackTrace();
			return -1L;
		}
		try {
			if(users != null && users.size() == 1 && 
					users.get(0).getAccepted() == Boolean.TRUE &&
					users.get(0).getType().equals(UserType.FACULTY.ordinal())) {
				Competition competition = new Competition();
				Date date = new Date(Calendar.getInstance().getTime().getTime());
				competition.setCreatedBy(users.get(0));
				competition.setCreatedDate(date);
				competition.setEndDate(endD);
				competition.setCompetitionName(competitionName);
				competition.setDescription(description);
				competition.setClassName(className);
				competition = save(competition);
				return competition.getId() ;
			}else {
				return -1L;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return -1L;
		}
		
	}

	@Override
	public List<Competition> findByCompetitionNameLikeIgnoreCase(String competitionName) {
		return (List<Competition>) competitionDAO.findByCompetitionNameLikeIgnoreCase(competitionName);
	}

	@Override
	public Long joinCompetition(Long userId, Long compId) {
		Optional<Competition> competitions = null;
		Optional<User> users = null;
		try {
			users = (Optional<User>)userDAO.findById(userId);
			competitions = findById(compId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		User user = null;
		Competition competition = null;
		if(users.isPresent() && competitions.isPresent()) {
			user = users.get();
			competition = competitions.get(); 
		}		
		if(user.getAccepted() != Boolean.TRUE || !user.getType().equals(UserType.STUDENT.ordinal())) {
			return Long.valueOf(-1);
		}
		try {
			Set<Submission> submissions = competition.getCompetitionUserSubmissions();
			Submission submission = new Submission();
			submission.setUser(user);
			submission.setCompetition(competition);
			submissions.add(submission);
			competition.setCompetitionUserSubmissions(submissions);
			save(competition);
			return competition.getId();
		} catch(Exception e) {
			e.printStackTrace();
			return Long.valueOf(-1);
		}
	}

	@Override
	public Long saveSubmission(Submission submission) {
		return submissionDAO.save(submission).getId();
	}

	@Override
	public Optional<User> findUserById(Long userId) {
		return userDAO.findById(userId);
	}
	
	
	
}
