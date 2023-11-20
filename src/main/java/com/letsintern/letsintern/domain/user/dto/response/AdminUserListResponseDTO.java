package com.letsintern.letsintern.domain.user.dto.response;

import com.letsintern.letsintern.domain.user.vo.AdminUserVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AdminUserListResponseDTO {

    private List<AdminUserVo> userList;

    @Builder
    private AdminUserListResponseDTO(List<AdminUserVo> userList) {
        this.userList = userList;
    }

    public static AdminUserListResponseDTO from(List<AdminUserVo> userList) {
        return AdminUserListResponseDTO.builder()
                .userList(userList)
                .build();
    }
}
