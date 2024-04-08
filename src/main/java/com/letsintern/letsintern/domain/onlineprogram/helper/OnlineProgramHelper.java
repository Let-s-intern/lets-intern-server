package com.letsintern.letsintern.domain.onlineprogram.helper;

import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import com.letsintern.letsintern.domain.onlineprogram.repository.OnlineProgramRepository;
import com.letsintern.letsintern.domain.onlineprogram.vo.OnlineProgramAdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnlineProgramHelper {

    private final OnlineProgramRepository onlineProgramRepository;

    public OnlineProgram saveOnlineProgram(OnlineProgram onlineProgram) {
        return onlineProgramRepository.save(onlineProgram);
    }

    public Page<OnlineProgramAdminVo> getOnlineProgramAdminList(Pageable pageable) {
        return onlineProgramRepository.findAllOnlineProgramAdminVos(pageable);
    }
}
