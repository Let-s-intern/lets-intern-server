package com.letsintern.letsintern.domain.notice.helper;

import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.notice.dto.request.NoticeCreateDTO;
import com.letsintern.letsintern.domain.notice.dto.request.NoticeUpdateDTO;
import com.letsintern.letsintern.domain.notice.exception.NoticeNotFound;
import com.letsintern.letsintern.domain.notice.mapper.NoticeMapper;
import com.letsintern.letsintern.domain.notice.repository.NoticeRepository;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeHelper {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;
    private final ProgramRepository programRepository;

    public Long createNotice(Long programId, NoticeCreateDTO noticeCreateDTO) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        return noticeRepository.save(noticeMapper.toEntity(program, noticeCreateDTO)).getId();
    }

    public Long updateNotice(Long noticeId, NoticeUpdateDTO noticeUpdateDTO) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> NoticeNotFound.EXCEPTION);
        if(noticeUpdateDTO.getType() != null)
            notice.setType(noticeUpdateDTO.getType());
        if(noticeUpdateDTO.getTitle() != null)
            notice.setTitle(noticeUpdateDTO.getTitle());
        if(noticeUpdateDTO.getLink() != null)
            notice.setLink(noticeUpdateDTO.getLink());
        return notice.getId();
    }

    public Page<Notice> getNoticeList(Long programId, Pageable pageable) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        return noticeRepository.findAllByProgramIdOrderByIdDesc(programId, pageable);
    }

}
