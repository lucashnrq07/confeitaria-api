package com.lucas.confeitaria_api.user.entities;

import javax.management.relation.Role;

public enum UserRole {
    ADMIN("admin"),
    CLIENT("client");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
