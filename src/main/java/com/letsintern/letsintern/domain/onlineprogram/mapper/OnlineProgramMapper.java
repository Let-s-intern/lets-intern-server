package com.letsintern.letsintern.domain.onlineprogram.mapper;

import com.letsintern.letsintern.domain.onlineprogram.domain.OnlineProgram;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.response.OnlineProgramAdminListResponse;
import com.letsintern.letsintern.domain.onlineprogram.dto.response.OnlineProgramAdminResponse;
import com.letsintern.letsintern.domain.onlineprogram.vo.OnlineProgramAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OnlineProgramMapper {

    public OnlineProgram toEntity(OnlineProgramCreateDTO onlineProgramCreateDTO, String s3Url) {
        return OnlineProgram.createOnlineProgram(onlineProgramCreateDTO, s3Url);
    }

    public OnlineProgramAdminListResponse toOnlineProgramAdminListResponse(Page<OnlineProgramAdminVo> onlineProgramAdminVos) {
        return OnlineProgramAdminListResponse.of(
                (onlineProgramAdminVos.hasContent()) ? onlineProgramAdminVos.getContent() : new ArrayList<>(),
                PageInfo.of(onlineProgramAdminVos)
        );
    }

    public OnlineProgramAdminResponse toOnlineProgramAdminResponse(OnlineProgram onlineProgram) {
        return OnlineProgramAdminResponse.from(onlineProgram);
    }
}
