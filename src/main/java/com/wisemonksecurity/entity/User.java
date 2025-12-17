package com.wisemonksecurity.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import lombok.Data;
//import org.springframework.context.annotation.Role;

@Entity
@Data
public class User {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String username;

	    @Column(unique = true)
	    @Email
	    private String email;

	    private String password;

	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name = "user_roles",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<Role> roles = new HashSet<>();
}
