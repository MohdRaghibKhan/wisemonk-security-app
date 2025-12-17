package com.wisemonksecurity.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/stats")
    public Map<String, Object> stats() {

        Map<String, Object> data = new HashMap<>();
        data.put("totalUsers", 50);
        data.put("lastLogin", "2025-12-16 10:00");

        return data;
    }
}
