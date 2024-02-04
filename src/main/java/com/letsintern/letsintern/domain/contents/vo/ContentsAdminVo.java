package com.letsintern.letsintern.domain.contents.vo;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.domain.ContentsType;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ContentsAdminVo {

    private Long id;

    private ContentsType type;

    private String title;

    private LocalDate createdAt;

    private ContentsTopic topic;

    private String link;

    private List<Long> fileIdList;

    @Builder
    public ContentsAdminVo(Long id, ContentsType type, String title, LocalDate createdAt, ContentsTopic topic, String link, String fileListStr) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.createdAt = createdAt;
        this.topic = topic;
        this.link = link;
        if(fileListStr != null) this.fileIdList = StringUtils.stringToList(fileListStr);
    }
}
