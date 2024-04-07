package com.letsintern.letsintern.domain.banner.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BannerType {
    MAIN,
    PROGRAM,
    LINE,
    POPUP
}
