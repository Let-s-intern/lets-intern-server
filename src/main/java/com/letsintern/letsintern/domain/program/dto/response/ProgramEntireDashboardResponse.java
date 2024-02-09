package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProgramEntireDashboardResponse {

    private ApplicationEntireDashboardVo myDashboard;
    private List<ApplicationEntireDashboardVo> dashboardList;
    private PageInfo pageInfo;

    @Builder
    private ProgramEntireDashboardResponse(ApplicationEntireDashboardVo myDashboard, Page<ApplicationEntireDashboardVo> dashboardList) {
        this.myDashboard = myDashboard;
        this.dashboardList = (dashboardList.hasContent()) ? dashboardList.getContent() : new ArrayList<>();
        this.pageInfo = PageInfo.of(dashboardList);
    }

    public static ProgramEntireDashboardResponse of(ApplicationEntireDashboardVo myDashboard, Page<ApplicationEntireDashboardVo> dashboardList) {
        return ProgramEntireDashboardResponse.builder()
                .myDashboard(myDashboard)
                .dashboardList(dashboardList)
                .build();
    }
}
