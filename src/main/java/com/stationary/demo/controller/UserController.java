package com.stationary.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.entities.User;
import com.stationary.demo.repos.UserRepository;
import com.stationary.demo.service.UserServices;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	UserServices userServices;

	@PostMapping("/user")
	@ResponseBody
	public User registerUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userServices.registerUser(user);	
	}
	
	@GetMapping("/getUser")
	public List<User> getUser(){
		return userRepository.findAll();
	}

	@GetMapping("/getUser/{email:.+}/")
	public User getUserEmail(@PathVariable("email") String email){
		return userRepository.findByEmail(email);
	}
	
}
