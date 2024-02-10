package com.letsintern.letsintern.domain.notice.dto.request;

import com.letsintern.letsintern.domain.notice.domain.NoticeType;
import lombok.Getter;

@Getter
public class NoticeUpdateDTO {

    private NoticeType type;

    private String title;

    private String link;

}
