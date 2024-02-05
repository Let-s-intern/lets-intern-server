package com.letsintern.letsintern.domain.attendance.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AttendanceCreateDTO {

    @NotNull
    private String link;

}
