package com.wisemonksecurity.controller;


import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wisemonksecurity.entity.Role;
import com.wisemonksecurity.entity.User;
import com.wisemonksecurity.exception.ResourceNotFoundException;
import com.wisemonksecurity.repo.RoleRepository;
import com.wisemonksecurity.repo.UserRepository;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/roles")
    public Role createRole(@RequestBody Role role) {
        return roleRepo.save(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/{id}/roles")
    public User assignRole(@PathVariable Long id, @RequestParam String roleName) throws BadRequestException {

    	User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        Role role = roleRepo.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + roleName));

        if (user.getRoles().contains(role)) {
            throw new BadRequestException("Role already assigned to user");
        }
        user.getRoles().add(role);
        return userRepo.save(user);
    }
}
