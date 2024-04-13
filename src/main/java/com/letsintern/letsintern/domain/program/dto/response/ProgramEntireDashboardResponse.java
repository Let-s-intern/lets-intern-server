package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProgramEntireDashboardResponse {
    private List<ApplicationEntireDashboardVo> dashboardList;
    private List<ApplicationWishJob> wishJobList;
    private PageInfo pageInfo;

    @Builder
    private ProgramEntireDashboardResponse(Page<ApplicationEntireDashboardVo> dashboardList, List<ApplicationWishJob> wishJobList) {
        this.dashboardList = dashboardList.hasContent() ? dashboardList.getContent() : new ArrayList<>();
        this.wishJobList = wishJobList;
        this.pageInfo = PageInfo.of(dashboardList);
    }

    public static ProgramEntireDashboardResponse of(Page<ApplicationEntireDashboardVo> dashboardList, List<ApplicationWishJob> wishJobList) {
        return ProgramEntireDashboardResponse.builder()
                .dashboardList(dashboardList)
                .wishJobList(wishJobList)
                .build();
    }
}
