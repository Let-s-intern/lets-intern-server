package com.letsintern.letsintern.domain.mission.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class MissionCannotCheckDone extends BaseErrorException {

    public static final MissionCannotCheckDone EXCEPTION = new MissionCannotCheckDone();

    private MissionCannotCheckDone() {
        super(MissionErrorCode.MISSION_CANNOT_CHECK_DONE);
    }
}
