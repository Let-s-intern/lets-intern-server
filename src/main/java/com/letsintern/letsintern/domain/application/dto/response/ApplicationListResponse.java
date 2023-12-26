package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApplicationListResponse {

    private List<Application> applicationList = new ArrayList<>();
    private PageInfo pageInfo;

    @Builder
    private ApplicationListResponse(Page<Application> applicationList) {
        if(applicationList.hasContent()) this.applicationList = applicationList.getContent();
        this.pageInfo = PageInfo.of(applicationList);
    }

    public static ApplicationListResponse from(Page<Application> applicationList) {
        return ApplicationListResponse.builder()
                .applicationList(applicationList)
                .build();
    }
}
