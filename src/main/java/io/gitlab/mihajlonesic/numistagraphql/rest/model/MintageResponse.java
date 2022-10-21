package io.gitlab.mihajlonesic.numistagraphql.rest.model;

import io.gitlab.mihajlonesic.numistagraphql.entity.Mintage;

public class MintageResponse {

    private Integer year;
    private Long number;
    private String comment;

    public MintageResponse() {
    }

    public MintageResponse(Mintage mintage) {
        this.year = mintage.getYear();
        this.number = mintage.getNumber();
        this.comment = mintage.getComment();
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
