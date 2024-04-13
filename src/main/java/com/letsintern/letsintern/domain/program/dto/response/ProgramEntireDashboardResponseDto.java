package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ProgramEntireDashboardResponseDto(
        List<ApplicationEntireDashboardVo> dashboardList,
        List<ApplicationWishJob> wishJobList,
        PageInfo pageInfo
) {
    public static ProgramEntireDashboardResponseDto of(Page<ApplicationEntireDashboardVo> dashboardList, List<ApplicationWishJob> wishJobList) {
        return ProgramEntireDashboardResponseDto.builder()
                .dashboardList(dashboardList.getContent())
                .wishJobList(wishJobList)
                .build();
    }
}
