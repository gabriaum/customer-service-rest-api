package com.gabriaum.service.entity;

import com.gabriaum.service.dto.TicketDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "ticket")
@Table(name = "ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "attendant_id")
    private User attendant;

    @NotBlank(message = "Message is mandatory")
    private String message;

    private String response;

    public Ticket(TicketDTO data) {

        this.sender = data.user();
        this.message = data.message();
    }
}