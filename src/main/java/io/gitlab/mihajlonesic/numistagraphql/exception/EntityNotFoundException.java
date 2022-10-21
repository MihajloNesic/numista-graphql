package io.gitlab.mihajlonesic.numistagraphql.exception;

import graphql.ErrorType;

public class EntityNotFoundException extends AbstractGraphqlException {

    public EntityNotFoundException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage, ErrorType.DataFetchingException);
    }
}
