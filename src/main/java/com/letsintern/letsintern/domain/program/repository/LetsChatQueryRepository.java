package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.domain.MailStatus;
import com.letsintern.letsintern.domain.program.vo.letschat.LetsChatDetailVo;
import com.letsintern.letsintern.domain.program.vo.letschat.LetsChatMentorInfoVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LetsChatQueryRepository {

    Optional<LetsChatDetailVo> findLetsChatDetailVo(Long letsChatId);

    Optional<LetsChatMentorInfoVo> findLetsChatMentorInfoVo(Long letsChatId);

    List<LetsChat> findAllLetsChatByMailStatusAndStartDate(MailStatus mailStatus, LocalDate startDate);
    List<LetsChat> findAllLetsChatByMailStatusAndEndDate(MailStatus mailStatus, LocalDateTime endDate);
}
