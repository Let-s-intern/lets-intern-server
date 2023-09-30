package com.letsintern.letsintern.domain.user.dto.response;

import com.letsintern.letsintern.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserTotalListDTO {

    private List<User> userList;

    @Builder
    private UserTotalListDTO(List<User> userList) {
        this.userList = userList;
    }

    public static UserTotalListDTO from(List<User> userList) {
        return UserTotalListDTO.builder()
                .userList(userList)
                .build();
    }
}
