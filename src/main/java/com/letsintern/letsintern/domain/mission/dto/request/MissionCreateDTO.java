package com.letsintern.letsintern.domain.mission.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionCreateDTO {

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @NotNull
    private Integer th;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

}
