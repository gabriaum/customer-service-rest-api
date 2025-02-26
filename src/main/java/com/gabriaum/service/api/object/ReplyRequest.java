package com.gabriaum.service.api.object;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyRequest {

    private final String message;
}
