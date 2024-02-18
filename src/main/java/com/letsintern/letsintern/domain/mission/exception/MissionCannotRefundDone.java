package com.letsintern.letsintern.domain.mission.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class MissionCannotRefundDone extends BaseErrorException {

    public static final MissionCannotRefundDone EXCEPTION = new MissionCannotRefundDone();

    private MissionCannotRefundDone() {
        super(MissionErrorCode.MISSION_CANNOT_REFUND_DONE);
    }
}
