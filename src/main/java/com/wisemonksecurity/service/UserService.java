package com.wisemonksecurity.service;

import com.wisemonksecurity.dto.AuthResponse;
import com.wisemonksecurity.dto.LoginRequest;
import com.wisemonksecurity.dto.RegisterRequest;

public interface UserService {
	 void register(RegisterRequest request);
	    AuthResponse login(LoginRequest request);
}
