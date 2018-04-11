package com.ooad.InClassComp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooad.InClassComp.doa.CompetitionDAO;
import com.ooad.InClassComp.model.Competition;
@Service
public class CompetitionFacadeImpl implements CompetitionFacade {
	
	@Autowired
	CompetitionDAO competitionDAO;
	
	
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
	
}
