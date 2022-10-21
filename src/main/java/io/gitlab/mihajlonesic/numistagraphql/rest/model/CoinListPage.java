package io.gitlab.mihajlonesic.numistagraphql.rest.model;

import io.gitlab.mihajlonesic.numistagraphql.model.PageModel;
import org.springframework.data.domain.Page;

public class CoinListPage extends PageModel<CoinListResponse> {

    public CoinListPage(Page<CoinListResponse> content, int page, int size) {
        super(content, page, size);
    }
}
