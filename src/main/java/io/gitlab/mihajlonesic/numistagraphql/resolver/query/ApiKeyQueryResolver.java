package io.gitlab.mihajlonesic.numistagraphql.resolver.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.gitlab.mihajlonesic.numistagraphql.entity.ApiKey;
import io.gitlab.mihajlonesic.numistagraphql.service.ApiKeyService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiKeyQueryResolver implements GraphQLQueryResolver {

    private final ApiKeyService apiKeyService;

    public ApiKeyQueryResolver(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    public ApiKey myKey() {
        return apiKeyService.getCurrentApiKey();
    }

    public List<ApiKey> apiKeys() {
        return apiKeyService.getAllApiKeys();
    }

    public ApiKey apiKey(Long id) {
        return apiKeyService.getApiKeyById(id);
    }
}
