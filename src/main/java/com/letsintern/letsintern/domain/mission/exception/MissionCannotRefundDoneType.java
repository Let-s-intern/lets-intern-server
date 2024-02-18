package com.letsintern.letsintern.domain.mission.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class MissionCannotRefundDoneType extends BaseErrorException {

    public static final MissionCannotRefundDoneType EXCEPTION = new MissionCannotRefundDoneType();

    private MissionCannotRefundDoneType() {
        super(MissionErrorCode.MISSION_CANNOT_REFUND_DONE_TYPE);
    }
}
