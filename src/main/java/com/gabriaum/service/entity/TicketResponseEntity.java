package com.gabriaum.service.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TicketResponseEntity {

    private long ticketId;

    private TicketSendEntity ticket;

    private final String user;
    private final String[] message;

    public TicketResponseEntity(String user, String... message) {
        this.user = user;
        this.message = message;
    }
}
