package com.ooad.InClassComp.doa;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ooad.InClassComp.model.Competition;


public interface CompetitionDAO extends CrudRepository<Competition, Long> {

	public List<Competition> findByCompetitionNameIgnoreCase(String competitionName);
	public List<Competition> findByCreatedDate(Date createdDate );
	public List<Competition> findByEndDate(Date endDate);
	public List<Competition> findByCompetitionNameOrderByCreatedDateDesc(String competitionName);
	public List<Competition> findByCompetitionNameOrderByEndDateDesc(String competitionName);
	public List<Competition> findByCompetitionNameLikeIgnoreCase(String competitionName);
	
}
