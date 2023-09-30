package com.letsintern.letsintern.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserExistRequestDTO {

    private String name;

    private String phoneNum;
}
