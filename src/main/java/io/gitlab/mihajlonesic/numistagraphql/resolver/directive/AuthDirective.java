package io.gitlab.mihajlonesic.numistagraphql.resolver.directive;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.language.ArrayValue;
import graphql.language.StringValue;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import io.gitlab.mihajlonesic.numistagraphql.entity.ApiKey;
import io.gitlab.mihajlonesic.numistagraphql.exception.UnauthorizedAccessException;
import io.gitlab.mihajlonesic.numistagraphql.repository.ApiKeyRepository;
import io.gitlab.mihajlonesic.numistagraphql.security.SecurityConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class AuthDirective implements SchemaDirectiveWiring {

    private final ApiKeyRepository apiKeyRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthDirective.class);

    public AuthDirective(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        List<String> allowedRoles = getRoles(environment, "allowRoles");
        List<String> deniedRoles = getRoles(environment, "denyRoles");

        DataFetcher<?> originalDataFetcher = environment.getFieldDataFetcher();
        DataFetcher<?> authDataFetcher = dataFetchingEnvironment -> {
            DefaultGraphQLServletContext contextMap = dataFetchingEnvironment.getContext();
            HttpServletRequest request = contextMap.getHttpServletRequest();
            String apiKey = request.getHeader(SecurityConfig.KEY_HEADER);
            ApiKey key = apiKeyRepository.getByKey(apiKey);
            String role = key.getRole().name().toLowerCase();

            if (allowedRoles.isEmpty() && deniedRoles.isEmpty()) {
                LOGGER.info("Access for '{}' is not properly defined", environment.getElement().getName());
                throw new UnauthorizedAccessException(2001, "Access for '" + environment.getElement().getName() + "' is not properly defined");
            }

            if ((!allowedRoles.isEmpty() && !allowedRoles.contains(role)) || (!deniedRoles.isEmpty() && deniedRoles.contains(role))) {
                LOGGER.info("'{}' is not allowed to access '{}'", key.getUsername(), environment.getElement().getName());
                throw new UnauthorizedAccessException(2000, "You are not allowed to access '" + environment.getElement().getName() + "'");
            }

            try {
                return originalDataFetcher.get(dataFetchingEnvironment);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        };

        return environment.setFieldDataFetcher(authDataFetcher);
    }

    @NotNull
    private static List<String> getRoles(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment, String rolesParam) {
        GraphQLArgument argument = environment.getDirective().getArgument(rolesParam);
        ArrayValue array = argument != null ? (ArrayValue) argument.getArgumentValue().getValue() : null;
        return array != null ?
                array.getValues()
                        .stream()
                        .map(v -> ((StringValue) v).getValue().toLowerCase())
                        .collect(Collectors.toList()) : List.of();
    }
}
