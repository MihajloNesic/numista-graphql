package io.gitlab.mihajlonesic.numistagraphql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiKeyException extends AuthenticationException {

    public ApiKeyException(String message) {
        super(message);
    }
}
