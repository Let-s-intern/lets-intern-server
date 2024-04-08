package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.program.domain.ProgramType;

public interface ProgramServiceFactory {
    ProgramService getProgramService(ProgramType programType);
}
