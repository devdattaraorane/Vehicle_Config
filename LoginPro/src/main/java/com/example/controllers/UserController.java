package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.LoginRequest;
import com.example.dtos.RegisterRequest;
import com.example.services.UserManager;


@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserManager manager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		
		try {
			String token=manager.login(request);
			
			return ResponseEntity.ok(token);
		}catch(RuntimeException ex) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
		}
	}
	
	@GetMapping("/oauth")
	public ResponseEntity<?> oauth(Authentication authentication) {

	    if (authentication == null || !(authentication.getPrincipal() instanceof OAuth2User)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body("OAuth authentication not found");
	    }

	    OAuth2User user = (OAuth2User) authentication.getPrincipal();
	    String email = user.getAttribute("email");

	    String token = manager.Outh(email);
	    return ResponseEntity.ok(token);
	}

	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

	    System.out.println(">>> REGISTER CONTROLLER HIT <<<");

	    try {
	        String token = manager.register(request);
	        return ResponseEntity.ok(token);
	    } catch (RuntimeException ex) {
	        ex.printStackTrace();
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}


}
