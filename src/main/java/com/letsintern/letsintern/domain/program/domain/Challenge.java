package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.program.domain.converter.ChallengeTypeConverter;
import com.letsintern.letsintern.domain.program.domain.converter.ProgramTopicConverter;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.global.utils.EnumValueUtils;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@DiscriminatorValue("challenge")
@Entity
public class Challenge extends Program {
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

    public Challenge(BaseProgramRequestDto requestDto, String zoomLink, String zoomPassword) {
        super(requestDto.programInfo(), zoomLink, zoomPassword);
        this.topic = EnumValueUtils.toEntityCode(ChallengeTopic.class, requestDto.challengeInfo().challengeTopic());
        this.challengeType = EnumValueUtils.toEntityCode(ChallengeType.class, requestDto.challengeInfo().challengeTopic());
        this.openKakaoLink = requestDto.challengeInfo().openKakaoLink();
        this.openKakaoPassword = requestDto.challengeInfo().openKakaoPassword();
    }

//    public static Challenge createChallenge(BaseProgramRequestDto requestDto, String zoomLink, String zoomPassword) {
//        return Challenge.builder()
//                .topic(EnumValueUtils.toEntityCode(ChallengeTopic.class, requestDto.challengeInfo().challengeTopic()))
//                .challengeType(EnumValueUtils.toEntityCode(ChallengeType.class, requestDto.challengeInfo().challengeType()))
//                .openKakaoLink(requestDto.challengeInfo().openKakaoLink())
//                .openKakaoPassword(requestDto.challengeInfo().openKakaoPassword())
//                .build();
//    }

    public void updateChallenge(BaseProgramRequestDto requestDto, ProgramStatus programStatus, String fqaList) {
        super.updateProgramInfo(requestDto.programInfo(), programStatus, fqaList);
        this.topic = updateValue(this.topic, EnumValueUtils.toEntityCode(ChallengeTopic.class, requestDto.challengeInfo().challengeTopic()));
        this.challengeType = updateValue(this.challengeType, EnumValueUtils.toEntityCode(ChallengeType.class, requestDto.challengeInfo().challengeType()));
        this.openKakaoLink = updateValue(this.openKakaoLink, requestDto.challengeInfo().openKakaoLink());
        this.openKakaoPassword = updateValue(this.openKakaoPassword, requestDto.challengeInfo().openKakaoPassword());
    }
}
