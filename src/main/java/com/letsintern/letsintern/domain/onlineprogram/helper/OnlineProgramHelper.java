package com.letsintern.letsintern.domain.onlineprogram.helper;

import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import com.letsintern.letsintern.domain.onlineprogram.repository.OnlineProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnlineProgramHelper {

    private final OnlineProgramRepository onlineProgramRepository;

    public OnlineProgram saveOnlineProgram(OnlineProgram onlineProgram) {
        return onlineProgramRepository.save(onlineProgram);
    }
}
