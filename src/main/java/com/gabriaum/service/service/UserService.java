package com.gabriaum.service.service;

import com.gabriaum.service.dto.UserDTO;
import com.gabriaum.service.entity.User;
import com.gabriaum.service.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Getter
public class UserService {

    @Autowired
    private UserRepository repository;

    public User register(UserDTO data) {

        User user = new User(data);

        save(user);

        return user;
    }

    public Collection<User> getAll() {
        return repository.findAll();
    }

    protected void save(User user) {
        repository.save(user);
    }
}
