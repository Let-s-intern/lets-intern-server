package com.letsintern.letsintern.domain.onlineprogram.service;

import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.response.OnlineProgramIdResponse;
import com.letsintern.letsintern.domain.onlineprogram.helper.OnlineProgramHelper;
import com.letsintern.letsintern.domain.onlineprogram.mapper.OnlineProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OnlineProgramService {

    private final OnlineProgramHelper onlineProgramHelper;
    private final OnlineProgramMapper onlineProgramMapper;

    public OnlineProgramIdResponse createOnlineProgram(OnlineProgramCreateDTO onlineProgramCreateDTO) {
        OnlineProgram newOnlineProgram = onlineProgramMapper.toEntity(onlineProgramCreateDTO);
        return onlineProgramMapper.toOnlineProgramIdResponse(onlineProgramHelper.saveOnlineProgram(newOnlineProgram));
    }
}
