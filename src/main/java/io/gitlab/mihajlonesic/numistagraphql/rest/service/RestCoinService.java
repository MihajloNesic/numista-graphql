package io.gitlab.mihajlonesic.numistagraphql.rest.service;

import io.gitlab.mihajlonesic.numistagraphql.entity.Coin;
import io.gitlab.mihajlonesic.numistagraphql.exception.EntityNotFoundException;
import io.gitlab.mihajlonesic.numistagraphql.repository.CoinRepository;
import io.gitlab.mihajlonesic.numistagraphql.rest.model.CoinListPage;
import io.gitlab.mihajlonesic.numistagraphql.rest.model.CoinListResponse;
import io.gitlab.mihajlonesic.numistagraphql.rest.model.CoinResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestCoinService {

    private final CoinRepository coinRepository;

    public RestCoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public CoinResponse getCoin(Long coinId) {
        Optional<Coin> coinOptional = coinRepository.findById(coinId);
        if (coinOptional.isEmpty()) {
            throw new EntityNotFoundException(1001, "Coin with id " + coinId + " was not found");
        }
        return new CoinResponse(coinOptional.get());
    }

    public CoinListPage getCoins(Long issuerId, int page, int size) {
        Page<Coin> coins = coinRepository.findByIssuerId(issuerId,
            PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "yearMin", "yearMax", "faceValue")));
        Page<CoinListResponse> coinList = coins.map(CoinListResponse::new);
        return new CoinListPage(coinList, page, size);
    }
}
