package com.letsintern.letsintern.domain.user.dto.response;

import com.letsintern.letsintern.domain.user.vo.AdminMangerVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AdminManagerListResponse {

    private List<AdminMangerVo> managerList;

    @Builder
    private AdminManagerListResponse(List<AdminMangerVo> managerList) {
        this.managerList = managerList;
    }

    public static AdminManagerListResponse from(List<AdminMangerVo> managerList) {
        return AdminManagerListResponse.builder()
                .managerList(managerList)
                .build();
    }
}
