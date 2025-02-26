package com.gabriaum.service.dto;

import com.gabriaum.service.entity.User;

public record TicketDTO(User user, String message) {
}
