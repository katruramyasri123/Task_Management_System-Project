package com.rgukt.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgukt.in.clients.UserClient;
import com.rgukt.in.clientsDto.UserDto;
import com.rgukt.in.enums.TaskStatus;
import com.rgukt.in.model.Task;
import com.rgukt.in.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserClient userClient;
	@PostMapping("/save")
	public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String authHeader){
		UserDto user=userClient.getUserProfile(authHeader);
		Task task1=null;
		try {
			task1 = taskService.createTask(task, user.getRole());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(task1,HttpStatus.CREATED);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader) throws Exception{
		UserDto user=userClient.getUserProfile(authHeader);

			Task task1 = taskService.getById(id);
		
		return new ResponseEntity<>(task1,HttpStatus.OK);
		
	}
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required=false) TaskStatus status, @RequestHeader("Authorization") String authHeader) throws Exception{
		UserDto user=userClient.getUserProfile(authHeader);

			List<Task> tasks = taskService.getAllTasks(status);
		
		return new ResponseEntity<>(tasks,HttpStatus.OK);
		
	}
	@PutMapping("/{taskid}/user/{userid}")
	public ResponseEntity<Task> getAssignedToUser(@PathVariable("taskid") Long taskid, @PathVariable("userid") Long userid, @RequestHeader("Authorization") String authHeader) throws Exception{
		UserDto user=userClient.getUserProfile(authHeader);

			Task task = taskService.assignedToUser(userid,taskid);
		
		return new ResponseEntity<>(task,HttpStatus.OK);
		
	}
	@GetMapping("/user")
	public ResponseEntity<List<Task>> getTasksByUserid(@RequestParam(required=false) TaskStatus status, @RequestHeader("Authorization") String authHeader) throws Exception{
		UserDto user=userClient.getUserProfile(authHeader);

			List<Task> task = taskService.getTaskByAssignedUserId(user.getId(),status);
		
		return new ResponseEntity<>(task,HttpStatus.OK);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTak(@RequestBody Task task, @RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id) throws Exception{
		UserDto user=userClient.getUserProfile(authHeader);

			Task task1 = taskService.updateTask(id,task,user.getId());
		
		return new ResponseEntity<>(task1,HttpStatus.OK);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletetask( @RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id) throws Exception{
		UserDto user=userClient.getUserProfile(authHeader);

			 taskService.deleteTask(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	@PutMapping("/{id}/complete")
	public ResponseEntity<Task> completeTask(   @RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id) throws Exception{
		UserDto user=userClient.getUserProfile(authHeader);

			Task task = taskService.completeTask(id);
		
		return new ResponseEntity<>(task,HttpStatus.OK);
		
	}
	
}
