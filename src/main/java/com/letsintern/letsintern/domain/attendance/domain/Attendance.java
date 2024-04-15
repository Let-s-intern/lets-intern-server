package com.letsintern.letsintern.domain.attendance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceCreateDTO;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.user.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "attendance")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @NotNull
    private String link;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AttendanceResult result;

    @Nullable
    private String comments;

    @NotNull
    private Boolean isRefunded;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    @JsonIgnore
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Builder
    private Attendance(Mission mission, String link, User user) {
        this.mission = mission;
        this.user = user;
        this.link = link;
        if (mission.getEndDate().isAfter(LocalDateTime.now())) this.status = AttendanceStatus.PRESENT;
        else this.status = AttendanceStatus.LATE;
        this.result = AttendanceResult.WAITING;
        this.isRefunded = false;
    }

    public static Attendance of(Mission mission, AttendanceCreateDTO attendanceCreateDTO, User user) {
        return Attendance.builder()
                .mission(mission)
                .user(user)
                .link(attendanceCreateDTO.getLink())
                .build();
    }
}
