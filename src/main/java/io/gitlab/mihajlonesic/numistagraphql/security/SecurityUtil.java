package io.gitlab.mihajlonesic.numistagraphql.security;

import io.gitlab.mihajlonesic.numistagraphql.entity.ApiKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static ApiKey getCurrentApiKey() {
        Authentication authentication = authentication();
        if (authentication != null && authentication.getPrincipal() instanceof ApiUserPrincipal) {
            ApiUserPrincipal user = (ApiUserPrincipal) authentication.getPrincipal();
            return user.getApiKey();
        }
        return null;
    }

    public static Long getCurrentApiKeyId() {
        Authentication authentication = authentication();
        if (authentication != null && authentication.getPrincipal() instanceof ApiUserPrincipal) {
            ApiUserPrincipal user = (ApiUserPrincipal) authentication.getPrincipal();
            return user.getApiKey().getId();
        }
        return null;
    }

    private static Authentication authentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

}
