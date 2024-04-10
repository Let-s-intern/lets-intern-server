package com.letsintern.letsintern.domain.onlineprogram.helper;

import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import com.letsintern.letsintern.domain.onlineprogram.exception.OnlineProgramNotFound;
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

    public OnlineProgram findOnlineProgramById(Long id) {
        return onlineProgramRepository.findById(id).orElseThrow(() -> OnlineProgramNotFound.EXCEPTION);
    }

    public void saveOnlineProgram(OnlineProgram onlineProgram) {
        onlineProgramRepository.save(onlineProgram);
    }

    public Page<OnlineProgramAdminVo> getOnlineProgramAdminList(Pageable pageable) {
        return onlineProgramRepository.findAllOnlineProgramAdminVos(pageable);
    }

    public void deleteOnlineProgram(OnlineProgram onlineProgram) {
        onlineProgramRepository.delete(onlineProgram);
    }
}
