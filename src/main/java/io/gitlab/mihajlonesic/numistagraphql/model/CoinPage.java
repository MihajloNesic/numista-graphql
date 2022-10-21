package io.gitlab.mihajlonesic.numistagraphql.model;

import io.gitlab.mihajlonesic.numistagraphql.entity.Coin;
import org.springframework.data.domain.Page;

public class CoinPage extends PageModel<Coin> {

    public CoinPage(Page<Coin> content, int page, int size) {
        super(content, page, size);
    }
}
