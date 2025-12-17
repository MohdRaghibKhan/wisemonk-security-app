package com.wisemonksecurity.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisteredEvent {
    private Long userId;
    private String email;
}
