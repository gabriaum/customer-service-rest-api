package com.gabriaum.service.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;

    private final int statusCode;
}
