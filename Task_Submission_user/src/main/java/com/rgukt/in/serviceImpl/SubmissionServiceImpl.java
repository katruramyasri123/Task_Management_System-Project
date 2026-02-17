package com.rgukt.in.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgukt.in.Model.Submission;
import com.rgukt.in.clients.TaskClient;
import com.rgukt.in.clients.UserClient;
import com.rgukt.in.clientsDto.TaskDto;
import com.rgukt.in.repo.SubmissionRepo;
import com.rgukt.in.service.SubmissionService;

@Service
public class SubmissionServiceImpl implements SubmissionService {
	@Autowired
	private UserClient userClient;
	@Autowired
	private SubmissionRepo subRepo;
	@Autowired
	private TaskClient taskClient;
	@Override
	public Submission submitTask(Long taskId, String githubLink, Long userId,String token) throws Exception {
		TaskDto task=taskClient.getTask(taskId, token);
		Submission s=new Submission();
		if(task!=null) {
			s.setGithubLink(githubLink);
			s.setTaskId(taskId);
			s.setUserId(userId);
			s.setSubmissionTime(LocalDateTime.now());
			return subRepo.save(s);
		}
		throw new Exception("Task not found with id"+taskId);
	}

	@Override
	public Submission getTaskSubmissionById(Long id) throws Exception {
		
		return subRepo.findById(id).orElseThrow(()->new Exception("Submission not found"));
	}

	@Override
	public List<Submission> getAllSubmissions() {
		return subRepo.findAll();
	}

	@Override
	public List<Submission> getTaskSubmissionByTaskId(Long taskId) {
		return subRepo.findAllByTaskId(taskId);
	}

	@Override
	public Submission acceptOrDecline(String token,Long id, String status) throws Exception {
		Submission s=getTaskSubmissionById(id);
		s.setStatus(status);
		if(status.equals("ACCEPT")) {
			try {
				taskClient.completeTask(token,s.getTaskId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return subRepo.save(s);
	}

	

}
