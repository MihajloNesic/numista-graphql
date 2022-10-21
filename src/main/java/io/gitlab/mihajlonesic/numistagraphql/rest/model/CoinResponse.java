package io.gitlab.mihajlonesic.numistagraphql.rest.model;

import io.gitlab.mihajlonesic.numistagraphql.entity.Coin;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Composition;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Shape;

import java.util.List;
import java.util.stream.Collectors;

public class CoinResponse {

    private Long id;
    private String title;
    private IssuerResponse issuer;
    private String referenceNumber;
    private Boolean demonetized;
    private Float faceValue;
    private Composition composition;
    private Double weight;
    private Double diameter;
    private Shape shape;
    private String obverseLettering;
    private String reverseLettering;
    private String obverseImage;
    private String reverseImage;

    private List<MintageResponse> mintage;

    public CoinResponse() {
    }

    public CoinResponse(Coin coin) {
        this.id = coin.getId();
        this.title = coin.getTitle();
        this.issuer = new IssuerResponse(coin.getIssuer());
        this.referenceNumber = coin.getReferenceNumber();
        this.demonetized = coin.getDemonetized();
        this.faceValue = coin.getFaceValue();
        this.composition = coin.getComposition();
        this.weight = coin.getWeight();
        this.diameter = coin.getDiameter();
        this.shape = coin.getShape();
        this.obverseLettering = coin.getObverseLettering();
        this.reverseLettering = coin.getReverseLettering();
        this.obverseImage = coin.getObverseImage();
        this.reverseImage = coin.getReverseImage();
        this.mintage = coin.getMintage().stream().map(MintageResponse::new).collect(Collectors.toList());
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

    public IssuerResponse getIssuer() {
        return issuer;
    }

    public void setIssuer(IssuerResponse issuer) {
        this.issuer = issuer;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Boolean getDemonetized() {
        return demonetized;
    }

    public void setDemonetized(Boolean demonetized) {
        this.demonetized = demonetized;
    }

    public Float getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Float faceValue) {
        this.faceValue = faceValue;
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

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public String getObverseLettering() {
        return obverseLettering;
    }

    public void setObverseLettering(String obverseLettering) {
        this.obverseLettering = obverseLettering;
    }

    public String getReverseLettering() {
        return reverseLettering;
    }

    public void setReverseLettering(String reverseLettering) {
        this.reverseLettering = reverseLettering;
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

    public List<MintageResponse> getMintage() {
        return mintage;
    }

    public void setMintage(List<MintageResponse> mintage) {
        this.mintage = mintage;
    }
}
