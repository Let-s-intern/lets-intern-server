package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ProgramEntireDashboardResponse {
    private List<ApplicationEntireDashboardVo> dashboardList = new ArrayList<>();

    private List<ApplicationWishJob> wishJobList;
    private PageInfo pageInfo;

    @Builder
    private ProgramEntireDashboardResponse(ApplicationEntireDashboardVo myDashboard, Page<ApplicationEntireDashboardVo> dashboardList, List<ApplicationWishJob> wishJobList) {
        this.dashboardList.add(0, myDashboard);
        if(dashboardList.hasContent()) {
            this.dashboardList.addAll(dashboardList.getContent());
        }
        this.wishJobList = wishJobList;
        this.pageInfo = PageInfo.of(dashboardList);
    }

    public static ProgramEntireDashboardResponse of(ApplicationEntireDashboardVo myDashboard, Page<ApplicationEntireDashboardVo> dashboardList, List<ApplicationWishJob> wishJobList) {
        return ProgramEntireDashboardResponse.builder()
                .myDashboard(myDashboard)
                .dashboardList(dashboardList)
                .wishJobList(wishJobList)
                .build();
    }
}
