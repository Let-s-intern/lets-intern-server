package com.letsintern.letsintern.domain.contents.vo;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.domain.ContentsType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ContentsAdminVo {

    private Long id;

    private ContentsType type;

    private String title;

    private LocalDate createdAt;

    private ContentsTopic topic;

    private String link;

    @Builder
    public ContentsAdminVo(Long id, ContentsType type, String title, LocalDate createdAt, ContentsTopic topic, String link) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.createdAt = createdAt;
        this.topic = topic;
        this.link = link;
    }
}
