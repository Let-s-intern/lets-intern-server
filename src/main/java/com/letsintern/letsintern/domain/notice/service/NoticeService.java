package com.letsintern.letsintern.domain.notice.service;

import com.letsintern.letsintern.domain.notice.dto.request.NoticeCreateDTO;
import com.letsintern.letsintern.domain.notice.dto.request.NoticeUpdateDTO;
import com.letsintern.letsintern.domain.notice.dto.response.NoticeIdResponse;
import com.letsintern.letsintern.domain.notice.dto.response.NoticeListResponse;
import com.letsintern.letsintern.domain.notice.helper.NoticeHelper;
import com.letsintern.letsintern.domain.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeHelper noticeHelper;
    private final NoticeMapper noticeMapper;

    @Transactional
    public NoticeIdResponse createNotice(Long programId, NoticeCreateDTO noticeCreateDTO) {
        return noticeMapper.toNoticeIdResponse(noticeHelper.createNotice(programId, noticeCreateDTO));
    }

    @Transactional
    public NoticeIdResponse updateNotice(Long noticeId, NoticeUpdateDTO noticeUpdateDTO) {
        return noticeMapper.toNoticeIdResponse(noticeHelper.updateNotice(noticeId, noticeUpdateDTO));
    }

    public void deleteNotice(Long noticeId) {
        noticeHelper.deleteNotice(noticeId);
    }

    public NoticeListResponse getNoticeList(Long programId, Pageable pageable) {
        return noticeMapper.toNoticeListResponse(noticeHelper.getNoticeList(programId, pageable));
    }
}
