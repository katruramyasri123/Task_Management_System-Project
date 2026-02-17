package com.rgukt.in.service;

import java.util.List;

import com.rgukt.in.Model.Submission;

public interface SubmissionService {
	public Submission submitTask(Long taskId, String githubLink, Long userId, String token) throws Exception;
	
	public Submission getTaskSubmissionById(Long id) throws Exception;
	public List<Submission> getAllSubmissions();
	public List<Submission> getTaskSubmissionByTaskId(Long taskId);
	public Submission acceptOrDecline(String token,Long id, String status) throws Exception ;
}
