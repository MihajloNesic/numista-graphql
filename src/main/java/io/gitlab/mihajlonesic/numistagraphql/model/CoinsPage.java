package io.gitlab.mihajlonesic.numistagraphql.model;

import io.gitlab.mihajlonesic.numistagraphql.entity.Coin;
import org.springframework.data.domain.Page;

public class CoinsPage extends PageModel<Coin> {

    private CoinsPage(Page<Coin> content, int page, int size) {
        super(content, page, size);
    }

    public static CoinsPage of(Page<Coin> content, int page, int size) {
        return new CoinsPage(content, page, size);
    }
}
