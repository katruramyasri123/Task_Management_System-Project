package com.rgukt.in.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/submission")
	public ResponseEntity<String> toHome(){
		return new ResponseEntity<>("Welcome to the submission service",HttpStatus.OK);
	}
	
}
