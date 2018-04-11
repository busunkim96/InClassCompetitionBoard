package com.ooad.InClassComp.service;

import java.util.List;
import java.util.Optional;

import com.ooad.InClassComp.model.Competition;
import com.ooad.InClassComp.model.Submission;
import com.ooad.InClassComp.model.User;

public interface CompetitionFacade {
	public List<Competition> findAll();
	public Optional<Competition> findById(Long compId);
	public Competition save(Competition competition);	
	public Long createCompetition(String userName, String competitionName,
			String endDate, String className,
			String description);
	public List<Competition> findByCompetitionNameLikeIgnoreCase(String competitionName);
	public Long joinCompetition(Long userId,Long compId);
	public Long saveSubmission(Submission submission);
	public Optional<User> findUserById(Long userId);
}
