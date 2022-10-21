package io.gitlab.mihajlonesic.numistagraphql.model;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageModel<T> {

    private List<T> data;
    private Boolean hasData;
    private Integer totalPages;
    private Integer totalElements;
    private Integer currentPage;
    private Integer currentSize;
    private Boolean hasNext;
    private Integer nextPage;
    private Boolean hasPrevious;
    private Integer previousPage;
    private Boolean first;
    private Boolean last;

    public PageModel(Page<T> content, int page, int size) {
        this.setData(content.getContent());
        this.setHasData(content.hasContent());
        this.setTotalPages(content.getTotalPages());
        this.setTotalElements((int) content.getTotalElements());
        this.setCurrentPage(page);
        this.setCurrentSize(size);
        this.setHasNext(content.hasNext());
        this.setNextPage(content.hasNext() ? page+1 : null);
        this.setHasPrevious(content.hasPrevious());
        this.setPreviousPage(content.hasPrevious() ? page-1 : null);
        this.setFirst(content.isFirst());
        this.setLast(content.isLast());
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Boolean getHasData() {
        return hasData;
    }

    public void setHasData(Boolean hasData) {
        this.hasData = hasData;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }
}
