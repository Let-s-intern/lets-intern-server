package com.letsintern.letsintern.domain.mission.dto.request;

import com.letsintern.letsintern.domain.mission.domain.MissionTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionCreateDTO {

    @NotNull
    private MissionType type;

    @NotNull
    private MissionTopic topic;

    @Nullable
    private Integer refund;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @NotNull
    private String guide;

    @NotNull
    private Integer th;

    @Nullable
    private List<Long> contentsIdList;

}
