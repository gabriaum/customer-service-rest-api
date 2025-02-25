package com.gabriaum.service.api;

import com.gabriaum.service.dto.AccountDTO;
import com.gabriaum.service.entity.AccountEntity;
import com.gabriaum.service.entity.service.AccountService;
import com.gabriaum.service.entity.type.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountAPI {

    @Autowired
    private AccountService service;

    @PostMapping("/register")
    @ResponseBody
    public AccountDTO register(AccountEntity entity, Role role) {

        AccountEntity account = service.register(entity, role);

        return new AccountDTO(account.getId(), account.getUser(), account.getPassword(), account.getDisplayName());
    }

    @PutMapping("account/{id}")
    @ResponseBody
    public AccountDTO updateAccount(@PathVariable("id") long id, AccountEntity entity) {

        AccountEntity account = service.updateAccount(id, entity);

        return new AccountDTO(account.getId(), account.getUser(), account.getPassword(), account.getDisplayName());
    }

    @PostMapping("/login")
    @ResponseBody
    public AccountDTO login(String user, String password) {
        return service.login(user, password);
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public AccountEntity getAccountById(@PathVariable("id") long id) {
        return service.getAccount(id);
    }

    @GetMapping("/user/{name}")
    @ResponseBody
    public AccountEntity getAccountByUser(@PathVariable("name") String user) {

        for (AccountEntity account : service.values()) {

            if (account.getUser().equals(user)) {
                return account;
            }
        }

        return null;
    }

    @DeleteMapping("/id/{id}")
    @ResponseBody
    public AccountEntity deleteAccount(@PathVariable("id") long id) {
        return service.remove(id);
    }

    @DeleteMapping("/user/{name}")
    @ResponseBody
    public AccountEntity deleteAccount(@PathVariable("name") String user) {

        for (AccountEntity account : service.values()) {

            if (account.getUser().equals(user)) {
                return service.remove(account.getId());
            }
        }

        return null;
    }

    @GetMapping
    @ResponseBody
    public Iterable<AccountEntity> getAccounts() {
        return service.values();
    }
}
