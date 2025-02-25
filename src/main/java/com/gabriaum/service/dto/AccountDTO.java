package com.gabriaum.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountDTO {

    protected final long id;

    protected final String user, password, displayName;
}