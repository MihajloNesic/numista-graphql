package io.gitlab.mihajlonesic.numistagraphql.service;

import io.gitlab.mihajlonesic.numistagraphql.entity.ApiKey;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.ApiKeyStatus;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Role;
import io.gitlab.mihajlonesic.numistagraphql.exception.EntityNotFoundException;
import io.gitlab.mihajlonesic.numistagraphql.exception.InputException;
import io.gitlab.mihajlonesic.numistagraphql.repository.ApiKeyRepository;
import io.gitlab.mihajlonesic.numistagraphql.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ApiKeyService.class);

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepository.findAllApiKeys();
    }

    public ApiKey getApiKeyById(Long id) {
        Optional<ApiKey> apiKeyOptional = apiKeyRepository.findById(id);
        if (apiKeyOptional.isEmpty()) {
            throw new EntityNotFoundException(1001, "Api key with id " + id + " was not found");
        }
        return apiKeyOptional.get();
    }

    public ApiKey getCurrentApiKey() {
        return SecurityUtil.getCurrentApiKey();
    }

    public ApiKey createApiKey(String username, Role role, Integer quota) {
        ApiKey apiKey = ApiKey.create(username, role, quota);
        return apiKeyRepository.save(apiKey);
    }

    public ApiKey updateApiKey(Long id, String username, ApiKeyStatus status, Role role, Integer quota, Boolean regenerateAuthKey) {
        Long currentApiKeyId = SecurityUtil.getCurrentApiKeyId();

        if (Objects.equals(id, currentApiKeyId)) {
            throw new InputException(1004, "You cannot edit your own api key");
        }

        ApiKey apiKey = getApiKeyById(id);
        apiKey.update(username, status, role, quota, regenerateAuthKey);
        return apiKeyRepository.save(apiKey);
    }

    public Boolean resetApiKeyUse(Long apiKeyId) {
        try {
            ApiKey apiKey = getApiKeyById(apiKeyId);
            apiKey.resetUsage();
            apiKeyRepository.save(apiKey);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    // <second> <minute> <hour> <day-of-month> <month> <day-of-week>
    // each day at 1am - 0 0 1 * * *
    @Scheduled(cron = "0 0 1 * * *")
    public void resetAllApiKeyUsages() {
        LocalDateTime today = LocalDateTime.now();
        LOGGER.info("[CRON JOB] resetting all api keys for " + today);
        apiKeyRepository.resetAllApiKeyUsages();
    }

}
