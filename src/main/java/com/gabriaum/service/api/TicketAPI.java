package com.gabriaum.service.api;

import com.gabriaum.service.api.object.ReplyRequest;
import com.gabriaum.service.api.response.ErrorResponse;
import com.gabriaum.service.dto.TicketDTO;
import com.gabriaum.service.entity.Ticket;
import com.gabriaum.service.entity.User;
import com.gabriaum.service.entity.type.Role;
import com.gabriaum.service.service.TicketService;
import com.gabriaum.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/ticket")
public class TicketAPI {

    @Autowired
    private TicketService service;

    @Autowired
    private UserService userService;

    @PostMapping("/create/{secretId}")
    public ResponseEntity<?> create(@PathVariable("secretId") UUID secretId, @RequestBody String message) {

        User user = userService.getRepository().findBySecretId(secretId);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value()));
        }

        if (user.getRole().equals(Role.SUPPORTER)) {

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse("User is not a supporter", HttpStatus.FORBIDDEN.value()));
        }

        TicketDTO data = new TicketDTO(user, message);

        Ticket ticket = service.create(data);

        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PutMapping("/reply/{secretId}/{ticketId}")
    public ResponseEntity<?> reply(@PathVariable("secretId") UUID secretId, @PathVariable("ticketId") long ticketId, @RequestBody ReplyRequest request) {

        String message = request.getMessage();

        if (message == null || message.trim().isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Message cannot be empty", HttpStatus.BAD_REQUEST.value()));
        }

        User user = userService.getRepository().findBySecretId(secretId);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value()));
        }

        if (!user.getRole().equals(Role.SUPPORTER)) {

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse("User is not a supporter", HttpStatus.FORBIDDEN.value()));
        }

        Ticket ticket = service.getRepository().findById(ticketId);

        if (ticket == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Ticket not found", HttpStatus.NOT_FOUND.value()));
        }

        ticket.setAttendant(user);
        ticket.setResponse(message);

        service.save(ticket);

        return ResponseEntity.ok(ticket);
    }


    @PostMapping("/close/{secretId}/{ticketId}")
    public ResponseEntity<?> close(@PathVariable("secretId") UUID secretId, @PathVariable("ticketId") long ticketId) {

        User user = userService.getRepository().findBySecretId(secretId);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value()));
        }

        if (!user.getRole().equals(Role.SUPPORTER)) {

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse("User is not a supporter", HttpStatus.FORBIDDEN.value()));
        }

        Ticket ticket = service.getRepository().findById(ticketId);

        if (ticket == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Ticket not found", HttpStatus.NOT_FOUND.value()));
        }

        service.delete(ticket);

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/user/{secretId}")
    public ResponseEntity<?> list(@PathVariable("secretId") UUID secretId) {

        User user = userService.getRepository().findBySecretId(secretId);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value()));
        }

        if (!user.getRole().equals(Role.SUPPORTER)) {

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse("User is not a supporter", HttpStatus.FORBIDDEN.value()));
        }

        Iterable<Ticket> tickets = service.getRepository().findAll();

        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Ticket>> list() {

        Iterable<Ticket> tickets = service.getRepository().findAll();

        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}