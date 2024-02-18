package com.letsintern.letsintern.domain.attendance.dto.request;

import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import lombok.Getter;

@Getter
public class AttendanceAdminUpdateDTO {

    private String link;

    private AttendanceStatus status;

    private AttendanceResult result;

    private String comments;

    private Boolean isRefunded;

}
