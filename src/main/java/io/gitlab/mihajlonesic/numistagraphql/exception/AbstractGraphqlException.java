package io.gitlab.mihajlonesic.numistagraphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractGraphqlException extends RuntimeException implements GraphQLError {

    private final int errorCode;
    private final String errorMessage;
    private final ErrorType errorType;

    public AbstractGraphqlException(int errorCode, String errorMessage, ErrorType errorType) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorType = errorType;
    }

    public AbstractGraphqlException(int errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();
        customAttributes.put("errorCode", this.errorCode);
        customAttributes.put("errorMessage", this.errorMessage);
        return customAttributes;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return this.errorType;
    }
}
