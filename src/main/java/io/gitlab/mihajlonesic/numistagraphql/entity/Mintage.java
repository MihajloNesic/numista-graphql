package io.gitlab.mihajlonesic.numistagraphql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mintage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coin_id", referencedColumnName = "id")
    private Coin coin;

    private Integer year;
    private Long number;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Deprecated
    public Mintage() {
    }

    public static Mintage create(Coin coin, Integer year, Long number, String comment) {
        Mintage mintage = new Mintage();
        mintage.setCoin(coin);
        mintage.setYear(year);
        mintage.setNumber(number);
        mintage.setComment(comment);
        return mintage;
    }

    public Mintage update(Coin coin, Integer year, Long number, String comment) {
        if (coin != null) {
            setCoin(coin);
        }
        if (year != null) {
            setYear(year);
        }
        if (number != null) {
            setNumber(number);
        }
        if (comment != null) {
            setComment(comment);
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
