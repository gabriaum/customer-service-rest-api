package com.gabriaum.service.dto;

import com.gabriaum.service.entity.type.Role;

public record UserDTO(String email, String password, String firstName, String lastName, Role role) {
}
