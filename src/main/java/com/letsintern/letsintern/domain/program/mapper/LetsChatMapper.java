package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.request.LetsChatMentorPasswordDto;
import com.letsintern.letsintern.domain.program.dto.response.BaseProgramResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.LetsChatMentorAfterSessionResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.LetsChatMentorPriorSessionResponseDto;
import com.letsintern.letsintern.domain.program.vo.letschat.LetsChatMentorInfoVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LetsChatMapper {

    public LetsChat toEntityLetsChat(BaseProgramRequestDto baseProgramRequestDto,
                                     ZoomMeetingCreateResponse zoomMeetingCreateResponse,
                                     String mentorPassword) {
        return LetsChat.createLetsChat(baseProgramRequestDto, zoomMeetingCreateResponse.join_url(), zoomMeetingCreateResponse.password(), mentorPassword);
    }

    public BaseProgramResponseDto<?> toBaseProgramResponseDto(LetsChat letsChat) {
        return BaseProgramResponseDto.of(letsChat);
    }

    public LetsChatMentorPasswordDto toLetsChatMentorPasswordDto(String mentorPassword) {
        return LetsChatMentorPasswordDto.of(mentorPassword);
    }

    public LetsChatMentorPriorSessionResponseDto toLetsChatMentorPriorSessionResponseDto(
            LetsChatMentorInfoVo letsChatMentorInfoVo,
            List<String> applyMotiveList,
            List<String> preQuestionList) {
        return LetsChatMentorPriorSessionResponseDto.of(letsChatMentorInfoVo, applyMotiveList, preQuestionList);
    }

    public LetsChatMentorAfterSessionResponseDto toLetsChatMentorAfterSessionResponseDto(String title, List<String> reviewList) {
        return LetsChatMentorAfterSessionResponseDto.of(title, reviewList);
    }
}
