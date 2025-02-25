package com.gabriaum.service.entity.service;

import com.gabriaum.service.entity.TicketResponseEntity;
import com.gabriaum.service.entity.TicketSendEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public class TicketService {

    @Service
    public static class Send extends HashMap<Long, TicketSendEntity> {

        public TicketSendEntity sendTicket(TicketSendEntity ticket) {

            put(ticket.ticketId(), ticket);

            return ticket;
        }

        public TicketSendEntity getTicket(long ticketId) {
            return get(ticketId);
        }

        public void removeTicket(long ticketId) {
            remove(ticketId);
        }
    }

    @Service
    public static class Response extends HashMap<Long, TicketResponseEntity> {

        public TicketResponseEntity sendResponse(TicketResponseEntity ticket) {

            put(ticket.getTicketId(), ticket);

            return ticket;
        }

        public TicketResponseEntity getResponse(long ticketId) {
            return get(ticketId);
        }

        public void removeResponse(long ticketId) {
            this.remove(ticketId);
        }
    }
}
