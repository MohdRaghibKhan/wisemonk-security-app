package com.wisemonksecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisemonksecurity.dto.AuthResponse;
import com.wisemonksecurity.dto.LoginRequest;
import com.wisemonksecurity.dto.RegisterRequest;
import com.wisemonksecurity.repo.UserRepository;
import com.wisemonksecurity.security.JwtUtil;
import com.wisemonksecurity.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class HomeController {
	  @Autowired
	    private UserServiceImpl userService;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private UserRepository userRepo;

	    @Autowired
	    private PasswordEncoder encoder;
	
	    @PostMapping("/register")
	    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
	        userService.register(request);
	        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
	    }
	    
//	    @PostMapping("/login")
//		public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
//
//	        User user = userRepo.findByEmail(request.getEmail())
//	                .orElseThrow(() -> 
//	                    new InvalidCredentialsException("Invalid email or password"));
//
//	        if (!encoder.matches(request.getPassword(), user.getPassword())) {
//	            throw new InvalidCredentialsException("Invalid email or password");
//	        }
//
//			Set<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
//
//			String token = jwtUtil.generateToken(user.getEmail(), roles);
//
//			return ResponseEntity.ok(new AuthResponse(token));
//		}

	    @PostMapping("/login")
	    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {

	        return ResponseEntity.ok(userService.login(request));
	    }
}
