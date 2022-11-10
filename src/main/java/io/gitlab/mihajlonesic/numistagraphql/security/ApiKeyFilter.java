package io.gitlab.mihajlonesic.numistagraphql.security;

import io.gitlab.mihajlonesic.numistagraphql.entity.ApiKey;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.ApiKeyStatus;
import io.gitlab.mihajlonesic.numistagraphql.exception.ApiKeyException;
import io.gitlab.mihajlonesic.numistagraphql.repository.ApiKeyRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ApiKeyFilter extends OncePerRequestFilter {

    @Autowired
    private ApiKeyRepository apiKeyRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    private ApiKeyEntryPoint apiKeyEntryPoint;

    private AntPathMatcher pathMatcher = new AntPathMatcher();
    private List<String> notSecuredEndpoints;

    public ApiKeyFilter(ApiKeyEntryPoint apiKeyEntryPoint, List<String> notSecuredEndpoints) {
        this.apiKeyEntryPoint = apiKeyEntryPoint;
        this.notSecuredEndpoints = notSecuredEndpoints;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String keyHeader = httpServletRequest.getHeader(SecurityConfig.KEY_HEADER);

        // check auth key from header
        if (keyHeader == null) {
            apiKeyEntryPoint.commence(httpServletRequest, httpServletResponse, new ApiKeyException("No API key provided for '" + httpServletRequest.getRequestURI() + "'"));
            return;
        }

        Optional<ApiKey> apiKey = apiKeyRepository.findByKey(keyHeader);

        // check if auth key is a valid key (from db)
        if (apiKey.isEmpty()) {
            apiKeyEntryPoint.commence(httpServletRequest, httpServletResponse, new ApiKeyException("Invalid API key"));
            return;
        }

        ApiKey api = apiKey.get();

        // check if api key is active
        if (!api.getStatus().equals(ApiKeyStatus.ACTIVE)) {
            apiKeyEntryPoint.commence(httpServletRequest, httpServletResponse, new ApiKeyException("Your API key is no longer active"));
            return;
        }

        // check if quota is used
        if (api.quotaUsed()) {
            apiKeyEntryPoint.commence(httpServletRequest, httpServletResponse, new ApiKeyException("You have used all your quota for today ("+api.getQuota()+")"));
            return;
        }

        // add auth context
        Authentication authenticationRequest = new  UsernamePasswordAuthenticationToken(api.getId(), api.getPassword());
        Authentication authenticationResult = authenticationManager.authenticate(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);

        // use 1 call from quota
        api.use();
        apiKeyRepository.save(api);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(@NotNull HttpServletRequest request) {
        return notSecuredEndpoints.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }
}
