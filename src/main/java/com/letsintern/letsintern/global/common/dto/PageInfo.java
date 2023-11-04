package com.letsintern.letsintern.global.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PageInfo {

    private final int pageNum;
    private final int pageSize;
    private final long totalElements;
    private final long totalPages;

    @Builder
    private PageInfo(int pageNum, int pageSize, long totalElements, long totalPages) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static PageInfo of(int pageNum, int pageSize, long totalElements, long totalPages) {
        return PageInfo.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }
}
