package com.letsintern.letsintern.domain.notice.dto.response;

import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NoticeListResponse {

    private List<Notice> noticeList = new ArrayList<>();
    private PageInfo pageInfo;

    @Builder
    private NoticeListResponse(Page<Notice> noticeList) {
        if(noticeList.hasContent()) this.noticeList = noticeList.getContent();
        this.pageInfo = PageInfo.of(noticeList);
    }

    public static NoticeListResponse from(Page<Notice> noticeList) {
        return NoticeListResponse.builder()
                .noticeList(noticeList)
                .build();
    }
}
