package com.gabriaum.service.entity.service;


import com.gabriaum.service.dto.AccountDTO;
import com.gabriaum.service.entity.AccountEntity;
import com.gabriaum.service.entity.type.Role;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService extends HashMap<Long, AccountEntity> {

    public AccountEntity register(AccountEntity accountEntity, Role role) {

        long id = size() + 1;

        accountEntity.setId(id);
        accountEntity.setRole(role);

        put(id, accountEntity);

        return accountEntity;
    }

    public AccountDTO login(String user, String password) {

        for (Map.Entry<Long, AccountEntity> entry : entrySet()) {

            AccountEntity accountEntity = entry.getValue();

            if (accountEntity.getUser().equals(user) && accountEntity.getPassword().equals(password)) {
                return new AccountDTO(accountEntity.getId(), accountEntity.getUser(), accountEntity.getPassword(), accountEntity.getDisplayName());
            }
        }

        return null;
    }

    public AccountEntity updateAccount(long id, AccountEntity entity) {

        AccountEntity account = get(id);

        account.setUser(entity.getUser());
        account.setPassword(entity.getPassword());
        account.setDisplayName(entity.getDisplayName());
        account.setRole(entity.getRole());

        return account;
    }

    public AccountEntity getAccount(long id) {
        return get(id);
    }
}