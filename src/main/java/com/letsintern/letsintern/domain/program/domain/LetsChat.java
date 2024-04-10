package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.program.domain.converter.MailStatusConverter;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import jakarta.persistence.*;
import lombok.*;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@DiscriminatorValue("lets_chat")
@Entity
public class LetsChat extends Program {
    @Column(length = 10, nullable = false)
    @Convert(converter = MailStatusConverter.class)
    @Builder.Default
    private MailStatus mailStatus = MailStatus.YET;
    @Column(nullable = false)
    private String mentorPassword;

    @Builder(access = AccessLevel.PRIVATE)
    private LetsChat(BaseProgramRequestDto baseProgramRequestDto, String zoomLink, String zoomPassword, String mentorPassword) {
        super(baseProgramRequestDto.programInfo(), zoomLink, zoomPassword);
        this.mentorPassword = mentorPassword;
    }

    public static LetsChat createLetsChat(BaseProgramRequestDto baseProgramRequestDto, String zoomLink, String zoomPassword, String mentorPassword) {
        return LetsChat.builder()
                .baseProgramRequestDto(baseProgramRequestDto)
                .zoomLink(zoomLink)
                .zoomPassword(zoomPassword)
                .mentorPassword(mentorPassword)
                .build();
    }

    public void updateLetsChat(BaseProgramRequestDto requestDto, ProgramStatus programStatus, String faqList) {
        super.updateProgramInfo(requestDto.programInfo(), programStatus, faqList);
        this.mentorPassword = updateValue(this.mentorPassword, requestDto.letsCHatRequestDto().mentorPassword());
    }
}
