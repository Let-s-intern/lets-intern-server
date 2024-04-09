package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.payment.domain.Payment;
import com.letsintern.letsintern.domain.program.domain.converter.ChallengeTypeConverter;
import com.letsintern.letsintern.domain.program.domain.converter.ProgramTopicConverter;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.request.ChallengeRequestDto;
import com.letsintern.letsintern.global.common.entity.BaseTimeEntity;
import com.letsintern.letsintern.global.utils.EnumValueUtils;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
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
    private ChallengeTopic topic;
    @Convert(converter = ChallengeTypeConverter.class)
    private ChallengeType challengeType;
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

    public Challenge(BaseProgramRequestDto requestDto) {
        super(requestDto.programInfo());
        this.topic = EnumValueUtils.toEntityCode(ChallengeTopic.class, requestDto.challengeInfo().challengeTopic());
        this.challengeType = EnumValueUtils.toEntityCode(ChallengeType.class, requestDto.challengeInfo().challengeTopic());
        this.openKakaoLink = requestDto.challengeInfo().openKakaoLink();
        this.openKakaoPassword = requestDto.challengeInfo().openKakaoPassword();
    }

    public static Challenge createChallenge(BaseProgramRequestDto requestDto) {
        return Challenge.builder()
                .topic(EnumValueUtils.toEntityCode(ChallengeTopic.class, requestDto.challengeInfo().challengeTopic()))
                .challengeType(EnumValueUtils.toEntityCode(ChallengeType.class, requestDto.challengeInfo().challengeType()))
                .openKakaoLink(requestDto.challengeInfo().openKakaoLink())
                .openKakaoPassword(requestDto.challengeInfo().openKakaoPassword())
                .build();
    }
}
