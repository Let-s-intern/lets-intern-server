package com.letsintern.letsintern.domain.program.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@DiscriminatorValue("bootcamp")
@Entity
public class Bootcamp extends Program{
    @JsonIgnore
    private Integer dum;

    public Bootcamp(BaseProgramRequestDto requestDto, String zoomLink, String zoomPassword) {
        super(requestDto.programInfo(), zoomLink, zoomPassword);
    }

    public void updateBootcamp(BaseProgramRequestDto requestDto, ProgramStatus programStatus, String fqaList) {
        super.updateProgramInfo(requestDto.programInfo(), programStatus, fqaList);
    }
}
