package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.vo.UserProgramVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class UserProgramVoResponse {

    private List<UserProgramVo> userProgramList;
    private PageInfo pageInfo;

    @Builder
    private UserProgramVoResponse(Page<UserProgramVo> userProgramList) {
        if(userProgramList.hasContent()) this.userProgramList = userProgramList.getContent();
        this.pageInfo = PageInfo.of(userProgramList);
    }

    public static UserProgramVoResponse from(Page<UserProgramVo> userProgramList) {
        return UserProgramVoResponse.builder()
                .userProgramList(userProgramList)
                .build();
    }
}
