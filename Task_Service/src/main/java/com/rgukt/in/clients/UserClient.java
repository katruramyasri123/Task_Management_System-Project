package com.rgukt.in.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.rgukt.in.clientsDto.UserDto;

@FeignClient(name="task-user-service", path="/api/users")
public interface UserClient {
	@GetMapping("/profile")
	public UserDto getUserProfile(@RequestHeader("Authorization") String authHeader) ;

}
