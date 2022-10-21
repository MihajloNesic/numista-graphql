package io.gitlab.mihajlonesic.numistagraphql.resolver.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.gitlab.mihajlonesic.numistagraphql.entity.Coin;
import io.gitlab.mihajlonesic.numistagraphql.model.CoinPage;
import io.gitlab.mihajlonesic.numistagraphql.service.CoinService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoinQueryResolver implements GraphQLQueryResolver {

    private final CoinService coinService;

    public CoinQueryResolver(CoinService coinService) {
        this.coinService = coinService;
    }

    public List<Coin> allCoins() {
        return coinService.getAllCoins();
    }

    public CoinPage coins(Long issuerId, int page, int size) {
        return coinService.getCoins(issuerId, page, size);
    }

    public Coin coin(Long id) {
        return coinService.getCoinById(id);
    }

    public List<Coin> coinsFromIssuer(Long issuerId) {
        return coinService.getCoinsFromIssuer(issuerId);
    }
}
