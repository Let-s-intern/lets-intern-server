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

    private MissionStatus status;

    private String title;

    private String contents;

    private String guide;

    private String template;

    private String comments;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer refund;

    private Integer refundTotal;

    private ContentsTopic essentialContentsTopic;

    private ContentsTopic additionalContentsTopic;

    private ContentsTopic limitedContentsTopic;


    public MissionAdminDetailVo(Long id, Integer th, MissionType type, MissionTopic topic, MissionStatus status, String title, String contents, String guide, String template, String comments,
                                LocalDateTime startDate, LocalDateTime endDate, Integer refund, Integer refundTotal,
                                ContentsTopic essentialContentsTopic, ContentsTopic additionalContentsTopic, ContentsTopic limitedContentsTopic) {
        this.id = id;
        this.th = th;
        this.type = type;
        this.topic = topic;
        this.status = status;
        this.title = title;
        this.contents = contents;
        this.guide = guide;
        this.template = template;
        this.comments = comments;
        this.startDate = startDate;
        this.endDate = endDate;
        this.refund = refund;
        this.refundTotal = refundTotal;
        if(essentialContentsTopic != null) this.essentialContentsTopic = essentialContentsTopic;
        if(additionalContentsTopic != null) this.additionalContentsTopic = additionalContentsTopic;
        if(limitedContentsTopic != null) this.limitedContentsTopic = limitedContentsTopic;
    }
}
