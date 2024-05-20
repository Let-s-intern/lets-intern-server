package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class AdminApplicationListResponse {

    private List<ApplicationAdminVo> applicationList;

    @Builder
    private AdminApplicationListResponse(List<ApplicationAdminVo> applicationList) {
        this.applicationList = applicationList;
    }

    public static AdminApplicationListResponse from(List<ApplicationAdminVo> applicationList) {
        return AdminApplicationListResponse.builder()
                .applicationList(applicationList)
                .build();
    }
}
