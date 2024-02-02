package com.letsintern.letsintern.domain.mission.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MissionAdminListResponse {

    private List<MissionAdminVo> missionList = new ArrayList<>();;
    private PageInfo pageInfo;

    @Builder
    private MissionAdminListResponse(Page<MissionAdminVo> missionList) {
        if(missionList.hasContent()) this.missionList = missionList.getContent();
        this.pageInfo = PageInfo.of(missionList);
    }

    public static MissionAdminListResponse from(Page<MissionAdminVo> missionList) {
        return MissionAdminListResponse.builder()
                .missionList(missionList)
                .build();
    }
}
