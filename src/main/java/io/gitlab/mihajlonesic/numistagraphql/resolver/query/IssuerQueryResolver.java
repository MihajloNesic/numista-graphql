package io.gitlab.mihajlonesic.numistagraphql.resolver.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.gitlab.mihajlonesic.numistagraphql.entity.Issuer;
import io.gitlab.mihajlonesic.numistagraphql.service.IssuerService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IssuerQueryResolver implements GraphQLQueryResolver {

    private final IssuerService issuerService;

    public IssuerQueryResolver(IssuerService issuerService) {
        this.issuerService = issuerService;
    }

    public List<Issuer> issuers() {
        return issuerService.getAllIssuers();
    }

    public Issuer issuer(Long id) {
        return issuerService.getIssuerById(id);
    }
}
