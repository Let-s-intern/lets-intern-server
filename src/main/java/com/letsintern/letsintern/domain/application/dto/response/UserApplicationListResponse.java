package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserApplicationListResponse {

    private List<ApplicationVo> userApplicationList = new ArrayList<>();
    private PageInfo pageInfo;

    @Builder
    private UserApplicationListResponse(Page<ApplicationVo> userApplicationList) {
        if(userApplicationList.hasContent()) this.userApplicationList = userApplicationList.getContent();
        this.pageInfo = PageInfo.of(userApplicationList);
    }

    public static UserApplicationListResponse from(Page<ApplicationVo> userApplicationList) {
        return UserApplicationListResponse.builder()
                .userApplicationList(userApplicationList)
                .build();
    }
}
