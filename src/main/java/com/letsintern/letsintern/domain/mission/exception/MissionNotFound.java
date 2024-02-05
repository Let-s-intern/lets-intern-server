package com.letsintern.letsintern.domain.mission.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class MissionNotFound extends BaseErrorException {

    public static final MissionNotFound EXCEPTION = new MissionNotFound();

    private MissionNotFound() {
        super(MissionErrorCode.MISSION_NOT_FOUND);
    }
}
