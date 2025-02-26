package com.gabriaum.service.repository;

import com.gabriaum.service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findById(long id);
}
