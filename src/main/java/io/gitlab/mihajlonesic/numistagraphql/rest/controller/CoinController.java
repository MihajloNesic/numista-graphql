package io.gitlab.mihajlonesic.numistagraphql.rest.controller;

import io.gitlab.mihajlonesic.numistagraphql.rest.model.CoinListPage;
import io.gitlab.mihajlonesic.numistagraphql.rest.model.CoinResponse;
import io.gitlab.mihajlonesic.numistagraphql.rest.service.RestCoinService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CoinController {

    private final RestCoinService coinService;

    public CoinController(RestCoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping(value = "/coins/{coinId}")
    public ResponseEntity<CoinResponse> getCoinById(@PathVariable("coinId") Long coinId) {
        return ResponseEntity.ok(coinService.getCoin(coinId));
    }

    @GetMapping(value = "/issuer/{issuerId}/coins")
    public ResponseEntity<CoinListPage> getCoinsByIssuer(
            @PathVariable("issuerId") Long issuerId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(coinService.getCoins(issuerId, page, size));
    }

}
