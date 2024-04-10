package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.program.domain.Bootcamp;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import org.springframework.stereotype.Component;

@Component
public class BootcampMapper {
    public Bootcamp toEntityBootcamp(BaseProgramRequestDto baseProgramRequestDto,
                                     ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
        return new Bootcamp(baseProgramRequestDto, zoomMeetingCreateResponse.join_url(), zoomMeetingCreateResponse.password());
    }
}
