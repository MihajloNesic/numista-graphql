package io.gitlab.mihajlonesic.numistagraphql.security;

import io.gitlab.mihajlonesic.numistagraphql.entity.ApiKey;
import io.gitlab.mihajlonesic.numistagraphql.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ApiUserDetailsService implements UserDetailsService {

    @Autowired
    private ApiKeyService apiKeyService;

    @Override
    public UserDetails loadUserByUsername(String apiKeyIdString) throws UsernameNotFoundException {
        ApiKey apiKey = apiKeyService.getApiKeyById(Long.valueOf(apiKeyIdString));
        return new ApiUserPrincipal(apiKey);
    }
}
