package com.rgukt.in.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgukt.in.Response.AuthResponse;
import com.rgukt.in.config.JwtProvider;
import com.rgukt.in.model.User;
import com.rgukt.in.repo.UserRepo;
import com.rgukt.in.request.LoginRequest;
import com.rgukt.in.service.CustomerUserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CustomerUserServiceImpl customerUserServiceImpl;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo userRepo;
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
		User isEmailExist=userRepo.findByEmail(user.getEmail());
		if(isEmailExist!=null) {
			throw new Exception("Email Already Exist");
		}
		User user1=new User();
		user1.setEmail(user.getEmail());
		user1.setFullName(user.getFullName());
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		user1.setRole(user.getRole());
		User savedUser=userRepo.save(user1);
		Authentication auth=new  UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
		String token=JwtProvider.generateToken(auth);
		AuthResponse authres=new AuthResponse();
		authres.setJwt(token);
		authres.setMessage("Registration success");
		authres.setStatus(true);
		return new ResponseEntity<>(authres,HttpStatus.OK);	
	}
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		Authentication auth=null;
		try {
			auth = authenticate(username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecurityContextHolder.getContext().setAuthentication(auth);
		String token=jwtProvider.generateToken(auth);
		AuthResponse res=new AuthResponse();
		res.setStatus(true);
		res.setMessage("login success");
		res.setJwt(token);
		return new ResponseEntity<>(res,HttpStatus.OK);
		

}
	public Authentication authenticate(String username,String password) throws Exception {
		UserDetails userDetails=customerUserServiceImpl.loadUserByUsername(username);
		if(userDetails==null){
			throw new Exception(" Username not exist...please signup!!!!");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Password incorrect...");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
