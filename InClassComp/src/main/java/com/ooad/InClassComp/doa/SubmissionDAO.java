package com.ooad.InClassComp.doa;

import org.springframework.data.repository.CrudRepository;

import com.ooad.InClassComp.model.Submission;

import java.util.List;

public interface SubmissionDAO extends CrudRepository<Submission, Long> {

    public List<Submission> findByUserId(Long userId);
}
