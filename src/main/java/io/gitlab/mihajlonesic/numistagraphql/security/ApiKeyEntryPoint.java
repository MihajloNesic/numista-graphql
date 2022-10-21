package io.gitlab.mihajlonesic.numistagraphql.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.gitlab.mihajlonesic.numistagraphql.exception.model.ApiErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class ApiKeyEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper;

    private final Logger LOGGER = LoggerFactory.getLogger(ApiKeyEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authException) throws IOException, ServletException {
        LOGGER.debug("Pre-authenticated entry point called. Rejecting access to {}", httpServletRequest.getRequestURL());

        try {
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            String message = status.getReasonPhrase();
            String messageDetails = authException.getMessage();

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            authException.printStackTrace(pw);

            LOGGER.error("AuthenticationException: " + authException.getMessage() + "\n trace: " + sw);

            ApiErrorDetails errorDetails = new ApiErrorDetails.Builder(authException)
                    .withStatus(status.value())
                    .withMessage(message)
                    .withMessageDetails(messageDetails)
                    .withPath(httpServletRequest.getRequestURI())
                    .build();

            httpServletResponse.setStatus(status.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(httpServletResponse.getWriter(), errorDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
