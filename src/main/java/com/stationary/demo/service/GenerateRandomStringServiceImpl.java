package com.stationary.demo.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GenerateRandomStringServiceImpl implements GenerateRandomStringService {

	@Override
	public String generateRandomString() {
		String SALTCHARS = "1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

}
