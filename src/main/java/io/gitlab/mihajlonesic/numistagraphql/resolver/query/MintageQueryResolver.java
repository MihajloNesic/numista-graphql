package io.gitlab.mihajlonesic.numistagraphql.resolver.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.gitlab.mihajlonesic.numistagraphql.entity.Mintage;
import io.gitlab.mihajlonesic.numistagraphql.service.MintageService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MintageQueryResolver implements GraphQLQueryResolver {

    private final MintageService mintageService;

    public MintageQueryResolver(MintageService mintageService) {
        this.mintageService = mintageService;
    }

    public List<Mintage> mintageByCoin(Long coinId) {
        return mintageService.getAllMintageByCoin(coinId);
    }
}
