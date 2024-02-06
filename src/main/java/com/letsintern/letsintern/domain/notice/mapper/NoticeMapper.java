package com.letsintern.letsintern.domain.notice.mapper;

import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.notice.dto.request.NoticeCreateDTO;
import com.letsintern.letsintern.domain.notice.dto.response.NoticeIdResponse;
import com.letsintern.letsintern.domain.notice.dto.response.NoticeListResponse;
import com.letsintern.letsintern.domain.program.domain.Program;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class NoticeMapper {

    public Notice toEntity(Program program, NoticeCreateDTO noticeCreateDTO) {
        return Notice.of(program, noticeCreateDTO);
    }

    public NoticeIdResponse toNoticeIdResponse(Long noticeId) {
        return NoticeIdResponse.from(noticeId);
    }

    public NoticeListResponse toNoticeListResponse(Page<Notice> noticeList) {
        return NoticeListResponse.from(noticeList);
    }
}
