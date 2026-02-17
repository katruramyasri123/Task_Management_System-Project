package com.rgukt.in.service;

import java.util.List;

import com.rgukt.in.model.User;

public interface UserService {
	public User getProfile(String token);
	public List<User> getUsers(String token);

}
