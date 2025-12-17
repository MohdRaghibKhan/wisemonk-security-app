package com.wisemonksecurity.service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wisemonksecurity.dto.AuthResponse;
import com.wisemonksecurity.dto.LoginRequest;
import com.wisemonksecurity.dto.RegisterRequest;
import com.wisemonksecurity.entity.Role;
import com.wisemonksecurity.entity.User;
import com.wisemonksecurity.event.dto.UserLoginEvent;
import com.wisemonksecurity.event.dto.UserRegisteredEvent;
import com.wisemonksecurity.exception.EmailAlreadyExistsException;
import com.wisemonksecurity.exception.InvalidCredentialsException;
import com.wisemonksecurity.repo.RoleRepository;
import com.wisemonksecurity.repo.UserRepository;
import com.wisemonksecurity.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private EventPublisher eventPublisher;

    public void register(RegisterRequest request) {

    	if (userRepo.existsByEmail(request.getEmail())) {
    	    throw new EmailAlreadyExistsException("Email already registered");
    	}

        Role userRole = roleRepo.findByName("USER")
                .orElseThrow();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles(Set.of(userRole));

        User savedUser = userRepo.save(user);
        eventPublisher.publishUserRegistered(
                new UserRegisteredEvent(savedUser.getId(), savedUser.getEmail())
        );
    }
    
    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        Set<String> roles = user.getRoles()
                .stream()
                .map(r -> "ROLE_" + r.getName()).collect(Collectors.toSet());

        String token = jwtUtil.generateToken(user.getEmail(), roles);
        eventPublisher.publishUserLogin(new UserLoginEvent(
                        user.getEmail(),
                        LocalDateTime.now().toString()));

        return new AuthResponse(token);
    }
}
