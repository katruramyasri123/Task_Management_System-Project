package com.rgukt.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgukt.in.model.User;
import com.rgukt.in.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@GetMapping("/profile")
	public ResponseEntity<User> getProfile(@RequestHeader("Authorization") String auth){
		User user=userServiceImpl.getProfile(auth);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getUsers(@RequestHeader("Authorization") String auth){
		List<User> users=userServiceImpl.getUsers(auth);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}

}
