package com.server.overtime.common;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getStatus();

    String getCode();

    String getMessage();
}
