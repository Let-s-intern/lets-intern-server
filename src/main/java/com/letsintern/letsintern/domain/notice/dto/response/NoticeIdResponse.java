package com.letsintern.letsintern.domain.notice.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoticeIdResponse {

    private Long noticeId;

    @Builder
    private NoticeIdResponse(Long noticeId) {
        this.noticeId = noticeId;
    }

    public static NoticeIdResponse from(Long noticeId) {
        return NoticeIdResponse.builder()
                .noticeId(noticeId)
                .build();
    }
}
