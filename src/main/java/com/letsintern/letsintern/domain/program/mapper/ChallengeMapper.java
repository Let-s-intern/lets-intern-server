package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.program.domain.Challenge;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.BaseProgramResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ChallengeMapper {
    public Challenge toEntityChallenge(BaseProgramRequestDto baseProgramRequestDto) {
        return Challenge.createChallenge(baseProgramRequestDto);
    }

    public BaseProgramResponseDto<?> toBaseProgramResponseDto(Challenge challenge) {
        return BaseProgramResponseDto.of(challenge);
    }
}
