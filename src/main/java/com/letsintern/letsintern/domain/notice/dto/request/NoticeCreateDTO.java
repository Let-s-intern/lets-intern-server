package com.letsintern.letsintern.domain.notice.dto.request;

import com.letsintern.letsintern.domain.notice.domain.NoticeType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NoticeCreateDTO {

    @NotNull
    private NoticeType type;

    @NotNull
    private String title;

    @NotNull
    private String link;

}
