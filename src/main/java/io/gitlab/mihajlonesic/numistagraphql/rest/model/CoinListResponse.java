package io.gitlab.mihajlonesic.numistagraphql.rest.model;

import io.gitlab.mihajlonesic.numistagraphql.entity.Coin;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Composition;

public class CoinListResponse {

    private Long id;
    private String title;
    private Integer yearMin;
    private Integer yearMax;
    private Composition composition;
    private Double weight;
    private Double diameter;
    private String referenceNumber;
    private String obverseImage;
    private String reverseImage;

    public CoinListResponse() {
    }

    public CoinListResponse(Coin c) {
        this.id = c.getId();
        this.title = c.getTitle();
        this.yearMin = c.getYearMin();
        this.yearMax = c.getYearMax();
        this.composition = c.getComposition();
        this.weight = c.getWeight();
        this.diameter = c.getDiameter();
        this.referenceNumber = c.getReferenceNumber();
        this.obverseImage = c.getObverseImage();
        this.reverseImage = c.getReverseImage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearMin() {
        return yearMin;
    }

    public void setYearMin(Integer yearMin) {
        this.yearMin = yearMin;
    }

    public Integer getYearMax() {
        return yearMax;
    }

    public void setYearMax(Integer yearMax) {
        this.yearMax = yearMax;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getObverseImage() {
        return obverseImage;
    }

    public void setObverseImage(String obverseImage) {
        this.obverseImage = obverseImage;
    }

    public String getReverseImage() {
        return reverseImage;
    }

    public void setReverseImage(String reverseImage) {
        this.reverseImage = reverseImage;
    }
}
