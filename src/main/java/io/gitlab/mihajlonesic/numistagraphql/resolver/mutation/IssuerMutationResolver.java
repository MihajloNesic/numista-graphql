package io.gitlab.mihajlonesic.numistagraphql.resolver.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.gitlab.mihajlonesic.numistagraphql.entity.Issuer;
import io.gitlab.mihajlonesic.numistagraphql.service.IssuerService;
import org.springframework.stereotype.Component;

@Component
public class IssuerMutationResolver implements GraphQLMutationResolver {

    private final IssuerService issuerService;

    public IssuerMutationResolver(IssuerService issuerService) {
        this.issuerService = issuerService;
    }

    public Issuer createIssuer(String name) {
        return issuerService.createIssuer(name);
    }

    public Issuer updateIssuer(Long id, String name) {
        return issuerService.updateIssuer(id, name);
    }

    public Boolean deleteIssuer(Long id) {
        return issuerService.deleteIssuer(id);
    }
}
