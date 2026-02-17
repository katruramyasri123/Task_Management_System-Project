package com.rgukt.in.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/tasks")
	public ResponseEntity<String> getTasks(){
		return new ResponseEntity<>("Welcome to the task service",HttpStatus.OK);
	}

}
