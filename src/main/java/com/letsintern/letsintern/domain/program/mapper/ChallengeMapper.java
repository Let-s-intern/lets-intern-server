package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.program.domain.Challenge;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.BaseProgramResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import org.springframework.stereotype.Component;

@Component
public class ChallengeMapper {
    public Challenge toEntityChallenge(BaseProgramRequestDto baseProgramRequestDto,
                                       ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
        return new Challenge(baseProgramRequestDto, zoomMeetingCreateResponse.join_url(), zoomMeetingCreateResponse.password());
    }
}
