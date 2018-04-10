package com.ooad.InClassComp.service;

import java.util.List;
import java.util.Optional;

import com.ooad.InClassComp.model.Competition;

public interface CompetitionFacade {
	public List<Competition> findAll();
	public Optional<Competition> findById(Long compId);
	public Competition save(Competition competition);	
}
