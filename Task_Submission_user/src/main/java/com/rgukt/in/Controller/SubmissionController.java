package com.rgukt.in.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgukt.in.Model.Submission;
import com.rgukt.in.clients.TaskClient;
import com.rgukt.in.clients.UserClient;
import com.rgukt.in.clientsDto.TaskDto;
import com.rgukt.in.clientsDto.UserDto;
import com.rgukt.in.service.SubmissionService;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

	@Autowired
	private UserClient userClient;
	@Autowired
	private TaskClient taskClient;
	@Autowired
	private SubmissionService subService;

	@PostMapping("/submit")
	public ResponseEntity<Submission> submitTask(@RequestParam Long taskid, @RequestParam String githubLink,
			@RequestHeader("Authorization") String authHeader) throws Exception {
		UserDto user = userClient.getUserProfile(authHeader);
		Submission s = subService.submitTask(taskid, githubLink, user.getId(), authHeader);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Submission> getSubmissionById(@PathVariable("id") Long id,
			@RequestHeader("Authorization") String authHeader) throws Exception {
		UserDto user = userClient.getUserProfile(authHeader);
		Submission s = subService.getTaskSubmissionById(id);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Submission>> getAllSubmissions(@RequestHeader("Authorization") String authHeader) {
		UserDto user = userClient.getUserProfile(authHeader);
		List<Submission> submissions = subService.getAllSubmissions();
		return new ResponseEntity<>(submissions, HttpStatus.OK);
	}

	@GetMapping("/task/{taskid}")
	public ResponseEntity<List<Submission>> getSubmissionByTaskId(@RequestHeader("Authorization") String authHeader,
			@PathVariable("taskid") Long id) {
		UserDto user = userClient.getUserProfile(authHeader);
		List<Submission> s = subService.getTaskSubmissionByTaskId(id);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Submission> acceptOrDecline(@RequestHeader("Authorization") String authHeader,
			@RequestParam String status, @PathVariable("id") Long id) throws Exception {
		Submission s = subService.acceptOrDecline(authHeader, id, status);
		return new ResponseEntity<>(s, HttpStatus.OK);

	}

}
