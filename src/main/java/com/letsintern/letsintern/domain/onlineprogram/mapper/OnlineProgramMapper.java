package com.letsintern.letsintern.domain.onlineprogram.mapper;

import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.response.OnlineProgramIdResponse;
import org.springframework.stereotype.Component;

@Component
public class OnlineProgramMapper {

    public OnlineProgram toEntity(OnlineProgramCreateDTO onlineProgramCreateDTO) {
        return OnlineProgram.createOnlineProgram(onlineProgramCreateDTO);
    }

    public OnlineProgramIdResponse toOnlineProgramIdResponse(OnlineProgram onlineProgram) {
        return OnlineProgramIdResponse.from(onlineProgram);
    }
}
