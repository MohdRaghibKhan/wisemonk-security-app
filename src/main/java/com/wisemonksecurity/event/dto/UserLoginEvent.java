package com.wisemonksecurity.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginEvent {
    private String email;
    private String loginTime;
}
