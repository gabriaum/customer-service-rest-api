package com.gabriaum.service.entity;

import com.gabriaum.service.entity.type.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountEntity {

    protected long id;

    protected String user, password, displayName;

    private Role role;

    private long createdIn = System.currentTimeMillis();

    public AccountEntity(String user, String password, String displayName) {
        this.user = user;
        this.password = password;
        this.displayName = displayName;
    }
}