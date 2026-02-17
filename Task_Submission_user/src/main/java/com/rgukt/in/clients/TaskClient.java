package com.rgukt.in.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.rgukt.in.clientsDto.TaskDto;

@FeignClient(name="task-service" ,url="http://localhost:5002", path="/api/tasks")
public interface TaskClient {
	@GetMapping("/{id}")
	public TaskDto getTask(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader) throws Exception;
	@PutMapping("/{id}/complete")
	public TaskDto completeTask(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id) throws Exception;
	
}
