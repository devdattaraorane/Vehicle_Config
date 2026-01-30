package com.example.services;

import org.springframework.stereotype.Service;

import com.example.dtos.LoginRequest;
import com.example.dtos.RegisterRequest;


public interface UserManager {
	
	String login(LoginRequest request);
	
	String Outh(String email);
	String register(RegisterRequest request);

}
