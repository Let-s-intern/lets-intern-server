package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AdminApplicationListResponse {

    private List<ApplicationAdminVo> applicationList = new ArrayList<>();
    private PageInfo pageInfo;

    @Builder
    private AdminApplicationListResponse(Page<ApplicationAdminVo> applicationList) {
        if(applicationList.hasContent()) this.applicationList = applicationList.getContent();
        this.pageInfo = PageInfo.of(applicationList);
    }

    public static AdminApplicationListResponse from(Page<ApplicationAdminVo> applicationList) {
        return AdminApplicationListResponse.builder()
                .applicationList(applicationList)
                .build();
    }
}
