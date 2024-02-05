package com.letsintern.letsintern.domain.attendance.vo;

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

    private String link;

    private Boolean isRefund;

    @Builder
    public AttendanceAdminVo(Long id, User user, AttendanceStatus status, String link, Boolean isRefund) {
        this.id = id;
        this.userName = user.getName();
        this.userEmail = user.getEmail();
        this.userAccountType = user.getAccountType().getValue();
        this.userAccountNumber = user.getAccountNumber();
        this.status = status;
        this.link = link;
        this.isRefund =isRefund;
    }
}
