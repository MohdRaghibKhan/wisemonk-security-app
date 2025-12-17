//package com.wisemonksecurity.security;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.wisemonksecurity.entity.Role;
//import com.wisemonksecurity.repo.RoleRepository;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.wisemonksecurity.entity.Role;
//import com.wisemonksecurity.repo.RoleRepository;
//
//@Configuration
//public class DataInitializer {
//
//    @Bean
//    CommandLineRunner initRoles(RoleRepository roleRepo) {
//        return args -> {
//
//            if (roleRepo.findByName("USER").isEmpty()) {
//                Role userRole = new Role();
//                userRole.setName("USER");
//                roleRepo.save(userRole);
//            }
//
//            if (roleRepo.findByName("ADMIN").isEmpty()) {
//                Role adminRole = new Role();
//                adminRole.setName("ADMIN");
//                roleRepo.save(adminRole);
//            }
//        };
//    }
//}
