package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.payment.domain.Payment;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@DiscriminatorValue("bootcamp")
@Entity
public class Bootcamp extends Program{
    private Integer dum;

    public Bootcamp(BaseProgramRequestDto requestDto, String zoomLink, String zoomPassword) {
        super(requestDto.programInfo(), zoomLink, zoomPassword);
    }

    public void updateBootcamp(BaseProgramRequestDto requestDto, ProgramStatus programStatus, String fqaList) {
        super.updateProgramInfo(requestDto.programInfo(), programStatus, fqaList);
    }
}
