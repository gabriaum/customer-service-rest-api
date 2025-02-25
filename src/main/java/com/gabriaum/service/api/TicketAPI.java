package com.gabriaum.service.api;

import com.gabriaum.service.entity.TicketResponseEntity;
import com.gabriaum.service.entity.TicketSendEntity;
import com.gabriaum.service.entity.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping(value = "/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketAPI {

    @Autowired
    private TicketService.Send sendService;

    @Autowired
    private TicketService.Response responseService;

    @PostMapping("/send")
    @ResponseBody
    public TicketSendEntity sendTicket(String user, String... message) {

        long id = sendService.size() + 1;

        return sendService.sendTicket(new TicketSendEntity(id, user, message));
    }

    @GetMapping("/send")
    @ResponseBody
    public Collection<TicketSendEntity> getTickets() {
        return sendService.values();
    }

    @GetMapping("/send/{ticketId}")
    @ResponseBody
    public TicketSendEntity getTicket(@PathVariable("ticketId") long ticketId) {
        return sendService.getTicket(ticketId);
    }

    @DeleteMapping("/send/{ticketId}")
    @ResponseBody
    public void removeTicket(@PathVariable("ticketId") long ticketId) {
        sendService.removeTicket(ticketId);
    }

    @PostMapping("/response/ticketId/{ticketSentId}")
    @ResponseBody
    public TicketResponseEntity sendResponse(@PathVariable("ticketSentId") long ticketSentId, TicketResponseEntity entity) {

        long ticketId = sendService.size() + 1;

        TicketSendEntity ticket = sendService.getTicket(ticketSentId);

        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found");
        }

        entity.setTicketId(ticketId);
        entity.setTicket(ticket);

        return responseService.sendResponse(entity);
    }

    @GetMapping("/response/{ticketId}")
    @ResponseBody
    public TicketResponseEntity getResponse(@PathVariable("ticketId") long ticketId) {
        return responseService.getResponse(ticketId);
    }

    @DeleteMapping("/response/{ticketId}")
    @ResponseBody
    public void removeResponse(@PathVariable("ticketId") long ticketId) {
        responseService.removeResponse(ticketId);
    }

    @GetMapping("/response")
    @ResponseBody
    public Collection<TicketResponseEntity> getResponses() {
        return responseService.values();
    }
}
