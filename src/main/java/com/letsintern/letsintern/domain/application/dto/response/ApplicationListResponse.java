package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ApplicationListResponse {

    private List<ApplicationAdminVo> applicationList;

    @Builder
    private ApplicationListResponse(List<ApplicationAdminVo> applicationList) {
        this.applicationList = applicationList;
    }

    public static ApplicationListResponse from(List<ApplicationAdminVo> applicationList) {
        return ApplicationListResponse.builder()
                .applicationList(applicationList)
                .build();
    }
}
