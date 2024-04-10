package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.request.ZoomMeetingCreateDto;
import com.letsintern.letsintern.domain.program.dto.response.BaseProgramResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import com.letsintern.letsintern.domain.program.helper.LetsChatHelper;
import com.letsintern.letsintern.domain.program.helper.ZoomMeetingApiHelper;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("LETS_CHAT")
public class LetsChatServiceImpl implements ProgramService {

    private final ZoomMeetingApiHelper zoomMeetingApiHelper;
    private final LetsChatHelper letsChatHelper;

    @Override
    public BaseProgramResponseDto<?> getProgramForAdmin(Long programId) {
        return null;
    }

    @Override
    public ProgramListResponseDto getProgramList() {
        return null;
    }

    @Override
    public ProgramDetailResponseDto<?> getProgramDetail(Long programId, PrincipalDetails principalDetails) {
        return null;
    }

    @Override
    public void createProgram(BaseProgramRequestDto requestDto) {
        ZoomMeetingCreateResponse zoomMeetingCreateResponse = zoomMeetingApiHelper.createMeeting(requestDto.programInfo());
        String mentorPassword = letsChatHelper.generateMentorPassword();
        LetsChat letsChat = LetsChat.createLetsChat(requestDto, zoomMeetingCreateResponse, mentorPassword);
        letsChatHelper.saveLetsChat(letsChat);
    }

    @Override
    public void updateProgram(Long programId, BaseProgramRequestDto requestDto) {

    }

    @Override
    public void deleteProgram(Long programId) {

    }

}
