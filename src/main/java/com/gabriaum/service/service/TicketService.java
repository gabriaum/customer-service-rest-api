package com.gabriaum.service.service;

import com.gabriaum.service.dto.TicketDTO;
import com.gabriaum.service.entity.Ticket;
import com.gabriaum.service.repository.TicketRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class TicketService {

    @Autowired
    private TicketRepository repository;

    public Ticket create(TicketDTO data) {

        Ticket ticket = new Ticket(data);

        save(ticket);

        return ticket;
    }

    public void save(Ticket ticket) {
        repository.save(ticket);
    }

    public void delete(Ticket ticket) {
        repository.delete(ticket);
    }
}
