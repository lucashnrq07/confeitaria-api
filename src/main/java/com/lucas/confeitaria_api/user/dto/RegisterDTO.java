package com.lucas.confeitaria_api.user.dto;

import com.lucas.confeitaria_api.user.entities.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
