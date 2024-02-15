package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionAdminDetailVo {

    private Long id;

    private Integer th;

    private MissionType type;

    private MissionTopic topic;

    private String title;

    private String contents;

    private String guide;

    private String template;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private ContentsTopic essentialContentsTopic;

    private ContentsTopic additionalContentsTopic;

    private ContentsTopic limitedContentsTopic;

    private Integer refund;

    private Boolean isRefunded;

    public MissionAdminDetailVo(Long id, Integer th, MissionType type, MissionTopic topic, String title, String contents, String guide, String template, LocalDateTime startDate, LocalDateTime endDate,
                                ContentsTopic essentialContentsTopic, ContentsTopic additionalContentsTopic, ContentsTopic limitedContentsTopic, Integer refund, Boolean isRefunded) {
        this.id = id;
        this.th = th;
        this.type = type;
        this.topic = topic;
        this.title = title;
        this.contents = contents;
        this.guide = guide;
        this.template = template;
        this.startDate = startDate;
        this.endDate = endDate;
        if(essentialContentsTopic != null) this.essentialContentsTopic = essentialContentsTopic;
        if(additionalContentsTopic != null) this.additionalContentsTopic = additionalContentsTopic;
        if(limitedContentsTopic != null) this.limitedContentsTopic = limitedContentsTopic;
        this.refund = refund;
        this.isRefunded = isRefunded;
    }
}
