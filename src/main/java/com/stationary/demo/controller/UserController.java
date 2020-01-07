package com.stationary.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.entities.User;
import com.stationary.demo.repos.UserRepository;
import com.stationary.demo.service.UserServices;
import com.stationary.demo.utilities.ResponseLibrary;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	UserServices userServices;

	@PostMapping("/user")
	@ResponseBody
	public ResponseEntity<ResponseLibrary> registerUser(@RequestBody User user) {
		LOGGER.info("Api for creating user - Parameters are " + user);
		user.setPassword(encoder.encode(user.getPassword()));
		LOGGER.info("Password encrypted successfully");
		LOGGER.info("Creating the reponseLibrary object");
		ResponseLibrary res = new ResponseLibrary();
		LOGGER.info("registerUser method is called");
		res.setData(userServices.registerUser(user));
		res.setError(false);
		res.setMessage("User Created Successfully");
		res.setStatus(200);
		LOGGER.info("Return the ResponseEntity to the response body");
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
	}

}
