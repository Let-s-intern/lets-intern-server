package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;

public class LetsChatMapper {

    public LetsChat toEntityLetsChat(BaseProgramRequestDto baseProgramRequestDto,
                                     String mentorPassword,
                                     ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
        return LetsChat.createLetsChat(baseProgramRequestDto, zoomMeetingCreateResponse, mentorPassword);
    }
}
