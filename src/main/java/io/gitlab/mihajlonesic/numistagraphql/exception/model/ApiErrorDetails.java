package io.gitlab.mihajlonesic.numistagraphql.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorDetails {

    private Integer status;
    private String message;
    private String messageDetails;
    private String path;
    private String exceptionClassName;

    public ApiErrorDetails(Integer status, String message, String messageDetails, String path, String exceptionClassName) {
        this.status = status;
        this.message = message;
        this.messageDetails = messageDetails;
        this.path = path;
        this.exceptionClassName = exceptionClassName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(String messageDetails) {
        this.messageDetails = messageDetails;
    }

    public String getExceptionClassName() {
        return exceptionClassName;
    }

    public void setExceptionClassName(String exceptionClassName) {
        this.exceptionClassName = exceptionClassName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Builder for {@link ApiErrorDetails}
     */
    public static class Builder {

        private Integer status;
        private String message;
        private String messageDetails;
        private String path;
        private String exceptionClassName;

        public Builder(Exception exception) {
            this.exceptionClassName = exception.getClass().getSimpleName();
        }

        public Builder withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withMessageDetails(String messageDetails) {
            this.messageDetails = messageDetails;
            return this;
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public ApiErrorDetails build() {
            return new ApiErrorDetails(status, message, messageDetails, path, exceptionClassName);
        }
    }
}
