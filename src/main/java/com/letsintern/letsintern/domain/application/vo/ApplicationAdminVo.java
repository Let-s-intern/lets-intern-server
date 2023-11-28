package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.application.domain.InflowPath;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplicationAdminVo {

    Long id;
    Integer grade;
    String wishCompany;
    String wishJob;
    String applyMotive;
    String preQuestions;
    InflowPath inflowPath;
    Boolean isApproved;
    ApplicationStatus status;
    Boolean attendance;
    Long reviewId;
    String createdAt;

    Boolean isUser;    // guest, user
    String name;
    String phoneNum;
    String email;
    String university;
    String major;

    @Builder
    public ApplicationAdminVo(Long id, Integer grade, String wishCompany, String wishJob, String applyMotive, String preQuestions,
                               InflowPath inflowPath, Boolean isApproved, ApplicationStatus status, Boolean attendance, Long reviewId, String createdAt,
                              String guestName, String guestPhoneNum, String guestEmail, User user) {
        this.id = id;
        this.grade = grade;
        this.wishCompany = wishCompany;
        this.wishJob = wishJob;
        this.applyMotive = applyMotive;
        this.preQuestions = preQuestions;
        this.inflowPath = inflowPath;
        this.isApproved = isApproved;
        this.status = status;
        this.attendance = attendance;
        this.reviewId = reviewId;
        this.createdAt = createdAt;

        if(user != null) {
            this.isUser = true;
            this.name = user.getName();
            this.phoneNum = user.getPhoneNum();
            this.email = user.getEmail();
            this.university = user.getUniversity();
            this.major = user.getMajor();
        }

        else {
            this.isUser = false;
            this.name = guestName;
            this.phoneNum = guestPhoneNum;
            this.email = guestEmail;
        }
    }
}

