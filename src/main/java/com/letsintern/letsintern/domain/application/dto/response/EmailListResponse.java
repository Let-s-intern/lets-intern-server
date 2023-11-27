package com.letsintern.letsintern.domain.application.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class EmailListResponse {

    private List<String> approvedEmailList;

    private List<String> notApprovedEmailList;

    @Builder
    private EmailListResponse(List<String> approvedEmailList, List<String> notApprovedEmailList) {
        this.approvedEmailList = approvedEmailList;
        this.notApprovedEmailList = notApprovedEmailList;
    }

    public static EmailListResponse of(List<String> approvedEmailList, List<String> notApprovedEmailList) {
        return EmailListResponse.builder()
                .approvedEmailList(approvedEmailList)
                .notApprovedEmailList(notApprovedEmailList)
                .build();
    }
}
