package io.gitlab.mihajlonesic.numistagraphql.rest.model;

import io.gitlab.mihajlonesic.numistagraphql.entity.Issuer;

public class IssuerResponse {

    private Long id;
    private String name;

    public IssuerResponse() {
    }

    public IssuerResponse(Issuer issuer) {
        this.id = issuer.getId();
        this.name = issuer.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
