package com.letsintern.letsintern.domain.user.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminMangerVo {

    private Long id;
    private String name;

    @Builder
    public AdminMangerVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
