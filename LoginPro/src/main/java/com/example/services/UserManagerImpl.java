package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.JwtUtil;
import com.example.dtos.LoginRequest;
import com.example.dtos.RegisterRequest;
import com.example.models.CompanyStatus;
import com.example.models.User;
import com.example.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserManagerImpl implements UserManager {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private EmailService emailService;

	@Transactional   // 👈 ADD HERE
	@Override
	public String login(LoginRequest request) {

	    User user = userRepository.findByEmail(request.getEmail())
	            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

	    // 🚫 Already blocked
	    if (user.getCompanyStatus() == CompanyStatus.Blocked) {
	        throw new RuntimeException("User is blocked after 3 failed attempts");
	    }

	    // ❌ Wrong password
	    if (!request.getPassword().equals(user.getPassword())) { // replace with encoder later

	        int attempts = user.getFailedAttempts() + 1;
	        user.setFailedAttempts(attempts);

	        if (attempts >= 3) {
	            user.setCompanyStatus(CompanyStatus.Blocked);
	        }

	        userRepository.save(user);
	        throw new RuntimeException("Invalid email or password");
	    }

	    // ✅ Successful login
	    user.setFailedAttempts(0);
	    userRepository.save(user);

	    return jwtUtil.generateToken(user.getEmail());
	}


	@Override
	public String Outh(String email) {
	    return jwtUtil.generateToken(email);
	}
	
	
	@Transactional
	@Override
	public String register(RegisterRequest request) {

	    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
	        throw new RuntimeException("Email already registered");
	    }

	    User user = new User();
	    user.setCompanyName(request.getCompanyName());
	    user.setAuthName(request.getAuthName());
	    user.setDesignation(request.getDesignation());
	    user.setEmail(request.getEmail());
	    user.setPassword(request.getPassword()); // later encode
	    user.setMobileNo(request.getMobileNo());
	    user.setCompanyStatus(CompanyStatus.Active);
	    user.setFailedAttempts(0);
	    user.setAddress(request.getAddress());
	    user.setTelephone(request.getTelephone());

        
	    // ✅ SAVE USER
	    userRepository.save(user);

	    // 📧 SEND EMAIL (NEW FEATURE)
	    emailService.sendRegistrationEmail(
	        user.getEmail(),
	        user.getAuthName(),
	        request.getPassword()
	    );

	    return jwtUtil.generateToken(user.getEmail());
	}





	

}
