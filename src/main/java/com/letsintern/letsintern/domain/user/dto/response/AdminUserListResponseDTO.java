package com.letsintern.letsintern.domain.user.dto.response;

import com.letsintern.letsintern.domain.user.vo.AdminUserVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class AdminUserListResponseDTO {

    private List<AdminUserVo> userList;
    private PageInfo pageInfo;

    @Builder
    private AdminUserListResponseDTO(Page<AdminUserVo> userList) {
        if(userList.hasContent()) this.userList = userList.getContent();
        this.pageInfo = PageInfo.of(userList);
    }

    public static AdminUserListResponseDTO from(Page<AdminUserVo> userList) {
        return AdminUserListResponseDTO.builder()
                .userList(userList)
                .build();
    }
}
