package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionAdminDetailVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private String template;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private ContentsTopic essentialContentsTopic;

    private ContentsTopic additionalContentsTopic;

    private ContentsTopic limitedContentsTopic;

    private Boolean isRefunded;

    public MissionAdminDetailVo(Long id, Integer th, String title, String contents, String template, LocalDateTime startDate, LocalDateTime endDate,
                                ContentsTopic essentialContentsTopic, ContentsTopic additionalContentsTopic, ContentsTopic limitedContentsTopic, Boolean isRefunded) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.template = template;
        this.startDate = startDate;
        this.endDate = endDate;
        if(essentialContentsTopic != null) this.essentialContentsTopic = essentialContentsTopic;
        if(additionalContentsTopic != null) this.additionalContentsTopic = additionalContentsTopic;
        if(limitedContentsTopic != null) this.limitedContentsTopic = limitedContentsTopic;
        this.isRefunded = isRefunded;
    }
}
