package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.program.domain.Challenge;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ChallengeMapper {
    public Challenge toEntityChallenge(BaseProgramRequestDto baseProgramRequestDto) {
        return Challenge.createChallenge(baseProgramRequestDto);
    }
}
