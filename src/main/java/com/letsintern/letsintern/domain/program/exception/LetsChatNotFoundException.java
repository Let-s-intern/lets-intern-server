package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class LetsChatNotFoundException extends BaseErrorException {
    public static final LetsChatNotFoundException EXCEPTION = new LetsChatNotFoundException();
    private LetsChatNotFoundException() {
        super(ProgramErrorCode.LETS_CHAT_NOT_FOUND);
    }
}
