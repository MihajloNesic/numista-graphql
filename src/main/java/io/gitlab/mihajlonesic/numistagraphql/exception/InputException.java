package io.gitlab.mihajlonesic.numistagraphql.exception;

import graphql.ErrorType;

public class InputException extends AbstractGraphqlException {

    public InputException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage, ErrorType.ValidationError);
    }
}
