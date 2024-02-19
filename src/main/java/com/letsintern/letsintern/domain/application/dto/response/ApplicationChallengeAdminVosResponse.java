package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.vo.ApplicationChallengeAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApplicationChallengeAdminVosResponse {

    private List<ApplicationChallengeAdminVo> applicationList;
    private PageInfo pageInfo;

    @Builder
    private ApplicationChallengeAdminVosResponse(Page<ApplicationChallengeAdminVo> applicationList) {
        this.applicationList = applicationList.hasContent() ? applicationList.getContent() : new ArrayList<>();
        this.pageInfo = PageInfo.of(applicationList);
    }

    public static ApplicationChallengeAdminVosResponse from(Page<ApplicationChallengeAdminVo> applicationList) {
        return ApplicationChallengeAdminVosResponse.builder()
                .applicationList(applicationList)
                .build();
    }
}
