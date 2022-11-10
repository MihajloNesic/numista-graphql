package io.gitlab.mihajlonesic.numistagraphql.entity;

import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Composition;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Shape;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "issuer_id", referencedColumnName = "id")
    private Issuer issuer;

    private String referenceNumber;
    private Integer yearMin;
    private Integer yearMax;
    private Boolean demonetized;
    private Float faceValue;

    @Enumerated(EnumType.STRING)
    private Composition composition;
    private String compositionComment;

    private Double weight;
    private Double diameter;

    @Enumerated(EnumType.STRING)
    private Shape shape;

    @Column(columnDefinition = "TEXT")
    private String obverseLettering;

    @Column(columnDefinition = "TEXT")
    private String reverseLettering;

    @Column(columnDefinition = "TEXT")
    private String obverseImage;

    @Column(columnDefinition = "TEXT")
    private String reverseImage;

    private Integer numistaId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "coin")
    private List<Mintage> mintage = new ArrayList<>();

    @Deprecated
    public Coin() {
    }

    public static Coin create(String title, Issuer issuer, String referenceNumber, Integer yearMin, Integer yearMax, Boolean demonetized, Float faceValue, Composition composition, Double weight, Double diameter, Shape shape, String obverseLettering, String reverseLettering, String obverseImage, String reverseImage, Integer numistaId) {
        Coin coin = new Coin();
        coin.setTitle(title);
        coin.setIssuer(issuer);
        coin.setReferenceNumber(referenceNumber);
        coin.setYearMin(yearMin);
        coin.setYearMax(yearMax);
        coin.setDemonetized(demonetized);
        coin.setFaceValue(faceValue);
        coin.setComposition(composition);
        coin.setWeight(weight);
        coin.setDiameter(diameter);
        coin.setShape(shape);
        coin.setObverseLettering(obverseLettering);
        coin.setReverseLettering(reverseLettering);
        coin.setObverseImage(obverseImage);
        coin.setReverseImage(reverseImage);
        coin.setNumistaId(numistaId);
        return coin;
    }

    public Coin update(String title, Issuer issuer, String referenceNumber, Integer yearMin, Integer yearMax, Boolean demonetized, Float faceValue, Composition composition, Double weight, Double diameter, Shape shape, String obverseLettering, String reverseLettering, String obverseImage, String reverseImage, Integer numistaId) {
        if (title != null) {
            setTitle(title);
        }
        if (issuer != null) {
            setIssuer(issuer);
        }
        if (referenceNumber != null) {
            setReferenceNumber(referenceNumber);
        }
        if (yearMin != null) {
            setYearMin(yearMin);
        }
        if (yearMax != null) {
            setYearMax(yearMax);
        }
        if (demonetized != null) {
            setDemonetized(demonetized);
        }
        if (faceValue != null) {
            setFaceValue(faceValue);
        }
        if (composition != null) {
            setComposition(composition);
        }
        if (weight != null) {
            setWeight(weight);
        }
        if (diameter != null) {
            setDiameter(diameter);
        }
        if (shape != null) {
            setShape(shape);
        }
        if (obverseLettering != null) {
            setObverseLettering(obverseLettering);
        }
        if (reverseLettering != null) {
            setReverseLettering(reverseLettering);
        }
        if (obverseImage != null) {
            setObverseImage(obverseImage);
        }
        if (reverseImage != null) {
            setReverseImage(reverseImage);
        }
        if (numistaId != null) {
            setNumistaId(numistaId);
        }
        return this;
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

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
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

    public String getComposition() {
        if (composition == null) {
            return null;
        }
        if (compositionComment != null) {
            return composition.getDisplayName() + " (" + compositionComment + ")";
        }
        return composition.getDisplayName();
    }

    public Composition getCompositionEnum() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public String getCompositionComment() {
        return compositionComment;
    }

    public void setCompositionComment(String compositionComment) {
        this.compositionComment = compositionComment;
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

    public String getShape() {
        return shape.getDisplayName();
    }

    public Shape getShapeEnum() {
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

    public Integer getNumistaId() {
        return numistaId;
    }

    public void setNumistaId(Integer numistaId) {
        this.numistaId = numistaId;
    }

    public List<Mintage> getMintage() {
        return mintage.stream()
                .sorted(Comparator.comparing(Mintage::getYear, Comparator.nullsFirst(Comparator.reverseOrder())).reversed())
                .collect(Collectors.toList());
    }
}
