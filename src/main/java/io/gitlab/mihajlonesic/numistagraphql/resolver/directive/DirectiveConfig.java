package io.gitlab.mihajlonesic.numistagraphql.resolver.directive;

import graphql.kickstart.autoconfigure.tools.SchemaDirective;
import io.gitlab.mihajlonesic.numistagraphql.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectiveConfig {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Bean
    public SchemaDirective caseConvertDirective() {
        return new SchemaDirective("caseConvert", new CaseConvertDirective());
    }

    @Bean
    public SchemaDirective authDirective() {
        return new SchemaDirective("auth", new AuthDirective(apiKeyRepository));
    }
}
