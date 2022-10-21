package io.gitlab.mihajlonesic.numistagraphql.exception;

import graphql.ErrorType;

public class UnauthorizedAccessException extends AbstractGraphqlException {

    public UnauthorizedAccessException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage, ErrorType.DataFetchingException);
    }
}
