package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.program.domain.ProgramRequestType;


public interface ProgramServiceFactory {
    ProgramService getProgramService(ProgramRequestType programType);
}
