package com.letsintern.letsintern.domain.mission.dto.request;

import com.letsintern.letsintern.domain.mission.domain.MissionType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionCreateDTO {

    @NotNull
    private Integer refund;

    @NotNull
    private MissionType type;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @NotNull
    private Integer th;

    @Nullable
    private List<Long> contentsIdList;

}
