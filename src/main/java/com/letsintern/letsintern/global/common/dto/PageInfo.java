package com.letsintern.letsintern.global.common.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageInfo {

    private final int pageNum;
    private final int pageSize;
    private final long totalElements;
    private final long totalPages;

    @Builder
    private PageInfo(int pageNum, int pageSize, long totalElements, long totalPages) {
        this.pageNum = pageNum + 1;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static PageInfo of(Page<?> page) {
        return PageInfo.builder()
                .pageNum(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
