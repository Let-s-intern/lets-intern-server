package com.letsintern.letsintern.domain.mission.dto.request;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import jakarta.annotation.Nullable;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionUpdateDTO {

    private MissionType type;

    private MissionTopic topic;

    private MissionStatus status;

    private Integer refund;

    private String title;

    private String contents;

    private String guide;

    private Integer th;

    private String template;

    private ContentsTopic essentialContentsTopic;

    private ContentsTopic additionalContentsTopic;

    private ContentsTopic limitedContentsTopic;

    private Boolean isRefunded;
}
