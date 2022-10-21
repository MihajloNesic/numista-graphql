package io.gitlab.mihajlonesic.numistagraphql.service;

import io.gitlab.mihajlonesic.numistagraphql.entity.Coin;
import io.gitlab.mihajlonesic.numistagraphql.exception.EntityNotFoundException;
import io.gitlab.mihajlonesic.numistagraphql.model.CoinPage;
import io.gitlab.mihajlonesic.numistagraphql.repository.CoinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {

    private final CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public List<Coin> getAllCoins() {
        return coinRepository.findAllCoins();
    }

    public Coin getCoinById(Long coinId) {
        Optional<Coin> coinOptional = coinRepository.findById(coinId);
        if (coinOptional.isEmpty()) {
            throw new EntityNotFoundException(1001, "Coin with id " + coinId + " was not found");
        }
        return coinOptional.get();
    }

    public CoinPage getCoins(Long issuerId, int page, int size) {
        Page<Coin> coins = coinRepository.findByIssuerId(issuerId, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "yearMin").and(Sort.by(Sort.Direction.ASC, "yearMax")).and(Sort.by(Sort.Direction.ASC, "faceValue"))));
        return new CoinPage(coins, page, size);
    }

    public List<Coin> getCoinsFromIssuer(Long issuerId) {
        return coinRepository.findAllCoinsByIssuer(issuerId);
    }
}

