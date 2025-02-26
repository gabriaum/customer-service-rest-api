package com.gabriaum.service.api;

import com.gabriaum.service.api.response.ErrorResponse;
import com.gabriaum.service.dto.UserDTO;
import com.gabriaum.service.entity.User;
import com.gabriaum.service.repository.UserRepository;
import com.gabriaum.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController()
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO data) {

        User user = service.register(data);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<User> login(String email, String password) {
//
//        UserRepository repository = service.getRepository();
//
//        User user = repository.findByEmail(email);
//
//        if (user == null) {
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        if (!user.getPassword().equals(password)) {
//
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody User user) {

        UserRepository repository = service.getRepository();

        User existingUser = repository.findById(id).orElse(null);

        if (existingUser == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value()));
        }

        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        repository.save(existingUser);

        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        UserRepository repository = service.getRepository();

        User user = repository.findById(id).orElse(null);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value()));
        }

        repository.delete(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {

        UserRepository repository = service.getRepository();

        User user = repository.findByEmail(email);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value()));
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {

        UserRepository repository = service.getRepository();

        User user = repository.findById(id).orElse(null);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value()));
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
