package io.gitlab.mihajlonesic.numistagraphql.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Issuer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issuer")
    private List<Coin> coins = new ArrayList<>();

    @Deprecated
    public Issuer() {
    }

    public static Issuer create(String name) {
        Issuer issuer = new Issuer();
        issuer.setName(name);
        return issuer;
    }

    public Issuer update(String name) {
        if (name != null) {
            setName(name);
        }
        return this;
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

    public List<Coin> getCoins() {
        return coins;
    }
}
