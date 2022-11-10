package io.gitlab.mihajlonesic.numistagraphql.service;

import io.gitlab.mihajlonesic.numistagraphql.entity.Coin;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Composition;
import io.gitlab.mihajlonesic.numistagraphql.exception.EntityNotFoundException;
import io.gitlab.mihajlonesic.numistagraphql.model.CoinsPage;
import io.gitlab.mihajlonesic.numistagraphql.repository.CoinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    public CoinsPage getCoins(Long issuerId, Composition.Tag compositionTag, int page, int size) {
        Page<Coin> coins;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Direction.ASC, "yearMin", "yearMax", "faceValue"));
        if (compositionTag == null) {
            coins = coinRepository.findByIssuerId(issuerId, pageRequest);
        }
        else {
            coins = coinRepository.findByIssuerIdAndCompositionIn(issuerId, compositionTag.getItems(), pageRequest);
        }
        return CoinsPage.of(coins, page, size);
    }

    public CoinsPage searchCoins(String title, int page, int size) {
        Page<Coin> coins = coinRepository.findByTitleContainingIgnoreCase(title,
            PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "issuer", "yearMin", "yearMax", "faceValue")));
        return CoinsPage.of(coins, page, size);
    }
}

