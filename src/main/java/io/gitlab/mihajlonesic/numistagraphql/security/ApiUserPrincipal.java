package io.gitlab.mihajlonesic.numistagraphql.security;

import io.gitlab.mihajlonesic.numistagraphql.entity.ApiKey;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.ApiKeyStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ApiUserPrincipal implements UserDetails {

    private final ApiKey apiKey;

    public ApiUserPrincipal(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(apiKey.getRole().toString()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return apiKey.getPassword();
    }

    @Override
    public String getUsername() {
        return apiKey.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return apiKey.getStatus().equals(ApiKeyStatus.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return apiKey.getStatus().equals(ApiKeyStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return apiKey.getStatus().equals(ApiKeyStatus.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return apiKey.getStatus().equals(ApiKeyStatus.ACTIVE);
    }
}
