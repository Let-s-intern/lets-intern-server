package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.program.domain.converter.ProgramTopicConverter;
import com.letsintern.letsintern.domain.program.dto.request.ChallengeBasicRequestDto;
import com.letsintern.letsintern.global.utils.EnumValueUtils;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@DiscriminatorValue("challenge")
@Entity
public class Challenge extends Program {
    @Id
    @Column(name = "challenge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Convert(converter = ProgramTopicConverter.class)
    private ProgramTopic topic;
    @Column(nullable = false)
    @Builder.Default
    private Integer finalHeadCount = 0;
    @Column(nullable = false)
    private String openKakaoLink;
    @Column(length = 10)
    private String openKakaoPassword;
    @OneToMany(mappedBy = "challenge", orphanRemoval = true)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", orphanRemoval = true)
    @Builder.Default
    private List<Notice> noticeList = new ArrayList<>();

}
