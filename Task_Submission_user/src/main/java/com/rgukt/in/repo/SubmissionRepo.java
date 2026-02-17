package com.rgukt.in.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rgukt.in.Model.Submission;

import java.util.List;
import java.util.Optional;


public interface SubmissionRepo extends JpaRepository<Submission, Long>{
	 public  Optional<Submission> findById(Long id);
	 public List<Submission> findAllByTaskId(Long taskid);

}
