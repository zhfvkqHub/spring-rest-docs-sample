package com.raonsecure.sample.base.dto;

import java.util.List;

public class CustomPage<T> {

    private int pageNumber;       // 현재 페이지 번호
    private int pageSize;         // 페이지당 요소 수
    private long totalElements;   // 총 요소 수
    private int totalPages;       // 총 페이지 수
    private List<T> content;      // 현재 페이지의 요소들

    public CustomPage(int pageNumber, int pageSize, long totalElements, List<T> content) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public boolean isFirst() {
        return pageNumber == 0;
    }

    public boolean isLast() {
        return pageNumber == totalPages - 1;
    }
}
