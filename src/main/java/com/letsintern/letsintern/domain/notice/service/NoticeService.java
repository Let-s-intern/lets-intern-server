package com.letsintern.letsintern.domain.notice.service;

import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.notice.dto.request.NoticeCreateDTO;
import com.letsintern.letsintern.domain.notice.dto.request.NoticeUpdateDTO;
import com.letsintern.letsintern.domain.notice.dto.response.NoticeIdResponse;
import com.letsintern.letsintern.domain.notice.dto.response.NoticeListResponse;
import com.letsintern.letsintern.domain.notice.helper.NoticeHelper;
import com.letsintern.letsintern.domain.notice.mapper.NoticeMapper;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeHelper noticeHelper;
    private final NoticeMapper noticeMapper;
    private final ProgramHelper programHelper;

    @Transactional
    public NoticeIdResponse createNotice(Long programId, NoticeCreateDTO noticeCreateDTO) {
        Program program = programHelper.findProgramOrThrow(programId);
        Notice newNotice = createNoticeAndSave(program, noticeCreateDTO);
        return noticeMapper.toNoticeIdResponse(newNotice.getId());
    }

    @Transactional
    public NoticeIdResponse updateNotice(Long noticeId, NoticeUpdateDTO noticeUpdateDTO) {
        return noticeMapper.toNoticeIdResponse(noticeHelper.updateNotice(noticeId, noticeUpdateDTO));
    }

    public void deleteNotice(Long noticeId) {
        noticeHelper.deleteNotice(noticeId);
    }

    public NoticeListResponse getNoticeList(Long programId, Pageable pageable) {
        Page<Notice> noticePage = noticeHelper.getNoticeList(programId, pageable);
        return noticeMapper.toNoticeListResponse(noticePage);
    }

    private Notice createNoticeAndSave(Program program, NoticeCreateDTO noticeCreateDTO) {
        Notice notice = noticeMapper.toEntity(program, noticeCreateDTO);
        return noticeHelper.saveNotice(notice);
    }
}
