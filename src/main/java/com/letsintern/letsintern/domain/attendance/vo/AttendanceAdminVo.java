package com.letsintern.letsintern.domain.attendance.vo;

import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AttendanceAdminVo {

    private Long id;

    private String userName;

    private String userEmail;

    private String userAccountType;

    private String userAccountNumber;

    private AttendanceStatus status;

    private AttendanceResult result;

    private String link;

    private Boolean isRefund;

    private String comments;

    @Builder
    public AttendanceAdminVo(Long id, User user, AttendanceStatus status, AttendanceResult result, String link, Boolean isRefund, String comments) {
        this.id = id;
        this.userName = user.getName();
        this.userEmail = user.getEmail();
        this.userAccountType = user.getAccountType().getValue();
        this.userAccountNumber = user.getAccountNumber();
        this.status = status;
        this.result = result;
        this.link = link;
        this.isRefund = isRefund;
        this.comments = comments;
    }
}
