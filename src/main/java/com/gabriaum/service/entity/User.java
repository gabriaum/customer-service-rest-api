package com.gabriaum.service.entity;

import com.gabriaum.service.dto.UserDTO;
import com.gabriaum.service.entity.type.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private UUID secretId = UUID.randomUUID();

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToMany
//    @Column(name = "tickets")
//    private List<Ticket> ticketList;

    public User(UserDTO userDTO) {

        this.email = userDTO.email();
        this.password = userDTO.password();
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.role = userDTO.role();
    }
}
