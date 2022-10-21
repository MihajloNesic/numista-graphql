package io.gitlab.mihajlonesic.numistagraphql.resolver.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.gitlab.mihajlonesic.numistagraphql.entity.ApiKey;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Role;
import io.gitlab.mihajlonesic.numistagraphql.model.UpdateApiKeyRequest;
import io.gitlab.mihajlonesic.numistagraphql.service.ApiKeyService;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyMutationResolver implements GraphQLMutationResolver {

    private final ApiKeyService apiKeyService;

    public ApiKeyMutationResolver(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    public ApiKey createApiKey(String username, Role role, Integer quota) {
        return apiKeyService.createApiKey(username, role, quota);
    }

    public ApiKey updateApiKey(UpdateApiKeyRequest updateRequest) {
        return apiKeyService.updateApiKey(updateRequest.getId(), updateRequest.getUsername(), updateRequest.getStatus(), updateRequest.getRole(), updateRequest.getQuota(), updateRequest.getRegenerateAuthKey());
    }

    public Boolean resetApiKeyUsage(Long apiKeyId) {
        return apiKeyService.resetApiKeyUse(apiKeyId);
    }
}
