package com.letsintern.letsintern.domain.mission.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_CANNOT_CHECK_DONE(BAD_REQUEST, "MISSION_400_1", "확인되지 않은 출석이 존재하여 '확인완료' 상태 변경이 불가능합니다"),
    MISSION_CANNOT_REFUND_DONE(BAD_REQUEST, "MISSION_400_2", "환급되지 않은 출석이 존재하여 '환급완료' 상태 변경이 불가능합니다"),
    MISSION_CANNOT_REFUND_DONE_TYPE(BAD_REQUEST, "MISSION_400_3", "보증금 미션이 아니므로 '환급완료' 상태 변경이 불가능합니다"),
    MISSION_NOT_FOUND(NOT_FOUND, "MISSION_404_1", "존재하지 않는 미션입니다.");

    private HttpStatus status;
    private String code;
    private String reason;


    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
