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

    @Builder
    private UserApplicationListResponse(List<ApplicationVo> userApplicationList) {
        this.userApplicationList = userApplicationList;
    }

    public static UserApplicationListResponse from(List<ApplicationVo> userApplicationList) {
        return UserApplicationListResponse.builder()
                .userApplicationList(userApplicationList)
                .build();
    }
}
